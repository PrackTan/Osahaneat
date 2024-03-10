package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.payload.Respone.CategoryReponse;
import com.cybersoft.osahaneat.payload.Respone.MenuReponse;
import com.cybersoft.osahaneat.repository.CategoryRepository;
import com.cybersoft.osahaneat.service.imp.CategoryServiceImp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoryService implements CategoryServiceImp {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    CategoryRepository categoryRepository;
    private Gson gson = new Gson();
    @Override
    public List<CategoryReponse> getCategory() {
        try {

//            List<Category> categoryList1 = categoryRepository.findAll();
//            System.out.println("Kiểm tra test"+categoryList1);
//            List<Category> categoryList1 = categoryRepository.findAll();

            String dataRedis = (String) redisTemplate.opsForValue().get("category");
            //Empty List
            List<CategoryReponse> categoryReponseList = new ArrayList<>();

            if(dataRedis == null){
                System.out.println("chưa có data");
                PageRequest pageRequest = PageRequest.of(0,3, Sort.by("id"));
                Page<Category> categoryList = categoryRepository.findAll(pageRequest);
                for (Category data: categoryList) {
//                System.out.println("test "+categoryList);

                    CategoryReponse categoryReponse = new CategoryReponse();
                    categoryReponse.setName(data.getNameCate());
                    // tạo list menuReponse
                    List<MenuReponse> menuResponseList = new ArrayList<>();
                    //Duyệt từng phần tử gán vào list
//                System.out.println("kiểm tra: 0 "+data);
//
//                System.out.println("kiểm tra: 1"+data.getListMenuRestaurants());
//
//                System.out.println("kiểm tra: 2"+data.getListFood());

                    for (Food dataFood : data.getListFood())
                    {
                        MenuReponse menuResponse = new MenuReponse();
                        menuResponse.setTitle(dataFood.getTitle());
                        menuResponse.setFreeShip(dataFood.isFreeship());
                        menuResponse.setImage(dataFood.getImage());
                        menuResponseList.add(menuResponse);
                    }
                    // setMenu là list
                    categoryReponse.setMenuReponseList(menuResponseList);
                    categoryReponseList.add(categoryReponse);
                }

                String dataJson = gson.toJson(categoryReponseList);
                redisTemplate.opsForValue().set("category",dataJson);
            }else{
                Type listType = new TypeToken<List<CategoryReponse>>(){}.getType();
                categoryReponseList = gson.fromJson(dataRedis,listType);
                System.out.println("có data");
            }
            return categoryReponseList;

        }catch (Exception e){
            log.info("Error get menu and category"+e.getMessage());
            return null;
        }

    }
}
