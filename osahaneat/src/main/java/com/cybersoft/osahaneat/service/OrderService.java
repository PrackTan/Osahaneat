package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.*;
import com.cybersoft.osahaneat.entity.key.KeyOrderDetail;
import com.cybersoft.osahaneat.payload.Request.OrdersRequest;
import com.cybersoft.osahaneat.repository.OrdersDetailRepository;
import com.cybersoft.osahaneat.repository.OrdersRepository;
import com.cybersoft.osahaneat.service.imp.OrdersServiceImp;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class OrderService implements OrdersServiceImp {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrdersDetailRepository ordersDetailRepository;

    @Transactional
    @Override
    public boolean insertOrder(OrdersRequest ordersRequest) {
    try {
        Users user = new Users();
        user.setId(ordersRequest.getUserId());

        Restaurant restaurant = new Restaurant();
        restaurant.setId(ordersRequest.getResId());

        Orders orders = new Orders();
        orders.setUsers(user);
        orders.setRestaurant(restaurant);
        ordersRepository.save(orders);

        Set<OrderDetail> orderDetailList = new HashSet<>();
        for (int idFood :ordersRequest.getFoodIds()
        ) {

            Food food = new Food();
            food.setId(idFood);

            OrderDetail orderDetail = new OrderDetail();
            KeyOrderDetail keyOrderDetail = new KeyOrderDetail(orders.getId(),idFood);
            orderDetail.setKeyOrderDetail(keyOrderDetail);

            orderDetailList.add(orderDetail);

        }
        ordersDetailRepository.saveAll(orderDetailList);
        return true;
    }catch (Exception e){
        log.info("ERROR insert Order"+e.getMessage());
        return false;
    }
    }

}
