package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestaurantDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String category;
    private Boolean active;
    private BigDecimal deliveryFee;
    private String description; 
    private Long rating;
   
}
