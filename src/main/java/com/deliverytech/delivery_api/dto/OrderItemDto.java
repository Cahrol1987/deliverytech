package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderItemDto {
    private int quantity;
    private BigDecimal unityPrice;
    private BigDecimal subTotal;
    private Long product_id;

}
