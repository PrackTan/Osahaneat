package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.payload.Request.MenuRequest;
import com.cybersoft.osahaneat.repository.MenuRepository;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.MenuServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class MenuService implements MenuServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    MenuRepository menuRepository;
    @Override
    public boolean insertMenu(MenuRequest menuRequest, MultipartFile file) {
        try {
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Food food = new Food();
//                food.setId(menuRequest.getCateId());
                food.setTitle(menuRequest.getTitle());
                food.setPrice(Double.parseDouble(menuRequest.getPrice()));
                food.setImage(file.getOriginalFilename());
                food.setTimeShip(menuRequest.getTimeShip());
                food.setFreeship(menuRequest.isFreeship());

                Category category = new Category();
                category.setId(menuRequest.getCateId());

                food.setCategory(category);
                menuRepository.save(food);


            }
            return true;
        }catch (Exception e){
            log.info("Error insert Menu"+e.getMessage());
            return false;
        }
    }
}
