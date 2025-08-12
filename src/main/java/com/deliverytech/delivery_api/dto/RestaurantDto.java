package com.deliverytech.delivery_api.dto;

import lombok.Data;

@Data
public class RestaurantDto {
    private String name;
    private String description;
    
    public RestaurantDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public RestaurantDto() {
        super();
    }
   
    @Override
    public String toString() {
        return "RestaurantDTO{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
