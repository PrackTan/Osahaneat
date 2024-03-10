package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.*;
import com.cybersoft.osahaneat.payload.Request.RestaurantRequest;
import com.cybersoft.osahaneat.payload.Respone.CategoryReponse;
import com.cybersoft.osahaneat.payload.Respone.MenuReponse;
import com.cybersoft.osahaneat.payload.Respone.RestaurantReponse;
import com.cybersoft.osahaneat.repository.RatingRestaurantRepository;
import com.cybersoft.osahaneat.repository.RestaurantRepository;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.RestaurantServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RatingRestaurantRepository ratingRestaurantRepository;
    @Override
    public boolean insertRestaurant(RestaurantRequest restaurantRequest, MultipartFile file) {
        boolean isSuccess = false;
        try {
            boolean isSave = fileServiceImp.saveFile(file);
            if (isSave) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(restaurantRequest.getTitle());
                restaurant.setDescription(restaurantRequest.getDescription());
                restaurant.setSubtitle(restaurantRequest.getSubtitle());
                restaurant.setAddress(restaurantRequest.getAddress());
                restaurant.setFreeShip(restaurantRequest.isFreeship());
                restaurant.setImage(file.getOriginalFilename());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

                restaurant.setOpenDate(sdf.parse(restaurantRequest.getOpenDate()));
                restaurantRepository.save(restaurant);
            }
            return isSuccess = true;
        }catch (Exception e){
            log.error("Lỗi insert "+e.getMessage());
            return isSuccess;
        }

    }

    @Override
    public Resource downloadRestaurantFile(String fileName) {
        try {
            return fileServiceImp.loadFile(fileName);
        }catch (Exception e){
            log.error("Lỗi download file" +e);
            return null;
        }

    }

    @Override
    public List<RestaurantReponse> getRestaurants() {


            try{
                List<RestaurantReponse> restaurantReponses = new ArrayList<>();
                PageRequest pageRequest = PageRequest.of(0,6);
//            Page<Restaurant> listPageRestaurants = restaurantRepository.findAll(pageRequest);
                Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);
                for (Restaurant data: listData) {
                    RestaurantReponse restaurantReponse = new RestaurantReponse();
                    restaurantReponse.setId(data.getId());
                    restaurantReponse.setTitle(data.getTitle());
                    restaurantReponse.setImage(data.getImage());
                    restaurantReponse.setSubtitle(data.getSubtitle());
                    restaurantReponse.setFreeShip(data.isFreeShip());
                    restaurantReponse.setRating(calculateRating());
                    restaurantReponses.add(restaurantReponse);

                }
                return restaurantReponses;

            }catch (Exception e){
                log.error("Error getRestaurant"+e.getLocalizedMessage());
                return null;
            }

    }

    @Override
    public RestaurantReponse getRestaurantsById(int id) {
        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            RestaurantReponse restaurantReponse = new RestaurantReponse();

            if(restaurant.isPresent()){

                List<CategoryReponse> categoryReponsesList = new ArrayList<>();
                Restaurant restaurantData = restaurant.get();

                restaurantReponse.setTitle(restaurantData.getTitle());
                restaurantReponse.setImage(restaurantData.getImage());
                restaurantReponse.setSubtitle(restaurantData.getSubtitle());
                restaurantReponse.setRating(calculateRating());
                restaurantReponse.setFreeShip(restaurantData.isFreeShip());
                restaurantReponse.setOpenDate(String.valueOf(restaurantData.getOpenDate()));
                restaurantReponse.setDescription(restaurantData.getDescription());


                //// category
                for (MenuRestaurant menuRestaurant:restaurantData.getListMenuRestaurants()) {

                    CategoryReponse categoryReponse = new CategoryReponse();

                    categoryReponse.setName(menuRestaurant.getCategory().getNameCate());

                    List<MenuReponse> menuReponseList = new ArrayList<>();

                        //// menu
                    for (Food food:menuRestaurant.getCategory().getListFood()) {

                        MenuReponse menuResponse = new MenuReponse();
                        menuResponse.setId(food.getId());
                        menuResponse.setImage(food.getImage());
                        menuResponse.setTitle(food.getTitle());
                        menuResponse.setFreeShip(food.isFreeship());
                        menuResponse.setDescription(food.getDesciption());
                        menuResponse.setPrice(food.getPrice());
                        menuReponseList.add(menuResponse);

                    }

                    categoryReponse.setMenuReponseList(menuReponseList);
                    categoryReponsesList.add(categoryReponse);
                }
                restaurantReponse.setCategoryReponsesList(categoryReponsesList);
            }
            return restaurantReponse;

        }catch (Exception e){
            return null;
        }
    }

    private double calculateRating(){
        try{
            List<RatingRestaurant> list = ratingRestaurantRepository.findAll();
            double totalPoints = 0;
//            List<RatingRestaurant> list = ratingRestaurantRepository.findAll();
            for (RatingRestaurant data : list) {
                totalPoints += data.getRatePoint();
            }
//            double totalAven = totalPoints / listRatingRestaurants.size();

            return totalPoints / list.size();
        }catch (Exception e){
            log.error("Error by calculateRating"+e.getLocalizedMessage());
            return 0;
        }

    }

}
