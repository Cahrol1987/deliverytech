package com.deliverytech.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverytech.delivery_api.entity.Restaurant;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long>{ //aqui eu indico a classe com que vou trabalhar

}
