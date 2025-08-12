package com.deliverytech.delivery_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.dto.RestaurantDto;
import com.deliverytech.delivery_api.service.RestaurantService;

@RestController //Para saber que é uma controller
public class RestaurantController {
    private RestaurantService restaurantService;
 
    @RequestMapping("/api/restaurantes")
    public List<RestaurantDto> listRestaurants() {
        return restaurantService.findAll();
    }
}
