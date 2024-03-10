package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.payload.BaseReponse;
import com.cybersoft.osahaneat.payload.Request.OrdersRequest;
import com.cybersoft.osahaneat.service.imp.OrdersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OdersController {
    @Autowired
    OrdersServiceImp ordersServiceImpl;
    @PostMapping("")
    public ResponseEntity<?> getAllOders(@RequestBody OrdersRequest ordersRequest){
        boolean isSuccess = ordersServiceImpl.insertOrder(ordersRequest);
        BaseReponse baseReponse = new BaseReponse();
        baseReponse.setStatusCode(isSuccess?200:400);
        baseReponse.setMessage(isSuccess?"Insert Success":"Insert False");
        baseReponse.setSuccess(isSuccess?true:false);
        baseReponse.setObject(isSuccess);
        return new ResponseEntity<>(baseReponse, HttpStatus.OK);
    }
}
