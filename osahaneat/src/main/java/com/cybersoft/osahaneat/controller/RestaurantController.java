package com.cybersoft.osahaneat.controller;


import com.cybersoft.osahaneat.entity.RatingRestaurant;
import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.payload.BaseReponse;
import com.cybersoft.osahaneat.payload.Request.RestaurantRequest;
import com.cybersoft.osahaneat.payload.Respone.RestaurantReponse;
import com.cybersoft.osahaneat.repository.RatingRestaurantRepository;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantServiceImp restaurantServiceImp;
    @Autowired
    private FileServiceImp fileServiceImp;
    @Autowired
    private RatingRestaurantRepository ratingRestaurantRepository;
    @PostMapping("")
    public ResponseEntity<?> insertRestaurant(@ModelAttribute RestaurantRequest restaurantRequest, @RequestParam MultipartFile file){
        boolean isSuccess = restaurantServiceImp.insertRestaurant(restaurantRequest, file);
        BaseReponse baseReponse = new
                BaseReponse(isSuccess?200:400
                ,isSuccess
                ,isSuccess?"Thêm thành công":"Thêm thất bại"
                ,isSuccess);
//        baseReponse.setStatusCode(200);
//        baseReponse.setMessage(isSuccess?"Save thành công":"Save thất bại");
//        baseReponse.setObject(isSuccess);
//        baseReponse.setSuccess(isSuccess);

        return new ResponseEntity<>(baseReponse, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getRestaurant(){
        List<RestaurantReponse> restaurantReponses = restaurantServiceImp.getRestaurants();

        BaseReponse baseReponse = new BaseReponse();
        baseReponse.setStatusCode(restaurantReponses.isEmpty()?400:200);
        baseReponse.setObject(restaurantReponses);
        baseReponse.setMessage("GET LIST RESTAURANT");
        baseReponse.setSuccess(!restaurantReponses.isEmpty() ? true : false);
        return new ResponseEntity<>(baseReponse, HttpStatus.OK);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<?> getFilleResults(@PathVariable String filename){
        Resource resource =  restaurantServiceImp.downloadRestaurantFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
    @GetMapping("/detail")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam int id){
       RestaurantReponse restaurantReponse = restaurantServiceImp.getRestaurantsById(id);
        BaseReponse baseReponse = new BaseReponse();
        baseReponse.setStatusCode(200);
        baseReponse.setSuccess(true);
        baseReponse.setMessage("GET RESTAURANT DETAIL");
        baseReponse.setObject(restaurantReponse);
        return new ResponseEntity<>(baseReponse,HttpStatus.OK);
    }
}
