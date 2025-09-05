package com.deliverytech.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverytech.delivery_api.entity.Orders;

public interface IOrderRepository extends JpaRepository <Orders, Long>, IOrderRepositoryCustom {

}
