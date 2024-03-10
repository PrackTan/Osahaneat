package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.payload.Request.OrdersRequest;

public interface OrdersServiceImp {
    boolean insertOrder(OrdersRequest ordersRequest);
}
