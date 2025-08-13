package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private BigDecimal price;
    private String category;
    private String description;
    private boolean available;
    private RestaurantDto restaurant;

    public ProductDto(String name, BigDecimal price, String category, String description, boolean available, RestaurantDto restaurant) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.available = available;
        this.restaurant = restaurant;
    }

    public ProductDto() {
        super();
    }

    @Override
    public String toString() {
        return "ProductDto{" +
               "name='" + name + '\'' +
               ", price=" + price +
               ", category='" + category + '\'' +
               ", description='" + description + '\'' +
               ", available=" + available +
               ", restaurantName=" + (restaurant != null ? restaurant.getName() : null) + //mostra apenas o nome do restaurante
               '}';
    }

}
