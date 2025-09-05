package com.deliverytech.delivery_api.repository;

import java.time.LocalDateTime;
import java.util.List;
import com.deliverytech.delivery_api.entity.Orders;
import com.deliverytech.delivery_api.entity.StatusOrder;

public interface IOrderRepositoryCustom {
    List <Orders> findOrderByClientId(Long id);    
    List <Orders> findOrderByStatus(StatusOrder status);
    List <Orders> findLastTenOrders();
    List <Orders> findOrdersByPeriod(LocalDateTime startDate, LocalDateTime endDate);
}


