package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderDto {
    private LocalDateTime orderDate;
    private String addressDelivery;
    private BigDecimal subTotal;
    private BigDecimal deliveryTax;
    private BigDecimal totalValue;
}


