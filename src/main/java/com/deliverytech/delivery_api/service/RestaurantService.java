package com.deliverytech.delivery_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.deliverytech.delivery_api.dto.RestaurantDto;

@Service
public interface RestaurantService {
public List <RestaurantDto> findAll();
}
