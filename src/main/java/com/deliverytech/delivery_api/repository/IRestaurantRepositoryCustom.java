package com.deliverytech.delivery_api.repository;
import com.deliverytech.delivery_api.entity.Restaurant;
import java.util.List;

public interface IRestaurantRepositoryCustom {
    List<Restaurant> findByCategoria(String category);
    List<Restaurant> findByAtivoTrue();
    List<Restaurant> findByNome(String name);
    //List<Restaurant> findByRestauranteId(Long id);
}
