package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.payload.Request.RestaurantRequest;
import com.cybersoft.osahaneat.payload.Respone.RestaurantReponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface RestaurantServiceImp {
    boolean insertRestaurant (RestaurantRequest restaurantRequest, MultipartFile file);
    Resource downloadRestaurantFile (String fileName);

    List<RestaurantReponse> getRestaurants();
    RestaurantReponse getRestaurantsById(int id);
}
