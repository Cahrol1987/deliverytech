package com.deliverytech.delivery_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.deliverytech.delivery_api.dto.RestaurantDto;


@Service
public interface RestaurantService {
public List <RestaurantDto> findAll();
public Long cadastrarRestaurante(RestaurantDto restaurantDto); 
public RestaurantDto atualizarRestaurante(Long id, RestaurantDto restaurantDto);
public List<RestaurantDto> findByCategoria(String category);
public List<RestaurantDto> findByNome(String name);
public List<RestaurantDto> findByAtivoTrue();

}
