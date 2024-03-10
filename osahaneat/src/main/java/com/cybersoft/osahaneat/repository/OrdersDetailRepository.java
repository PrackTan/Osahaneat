package com.cybersoft.osahaneat.repository;

import com.cybersoft.osahaneat.entity.OrderDetail;
import com.cybersoft.osahaneat.entity.key.KeyOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailRepository extends JpaRepository<OrderDetail, KeyOrderDetail> {
}
