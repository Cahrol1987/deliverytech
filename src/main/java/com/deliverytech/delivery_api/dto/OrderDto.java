package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.deliverytech.delivery_api.entity.StatusOrder;

import lombok.Data;

@Data
public class OrderDto {
    private LocalDateTime orderDate;
    private String addressDelivery;
    private BigDecimal subTotal;
    private BigDecimal deliveryTax;
    private BigDecimal totalValue;  
    private Long restaurant_id;
    private List<OrderItemDto> items;
    private Long client_id;
    private StatusOrder status;
    
}


