package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderItemDto {
    private int quantity;
    private BigDecimal unityPrice;
    private BigDecimal subTotal;
    private OrderDto order;
    private ProductDto product;

    public OrderItemDto(int quantity, BigDecimal unityPrice, BigDecimal subTotal, OrderDto order, ProductDto product) {
        this.quantity = quantity;
        this.unityPrice = unityPrice;
        this.subTotal = subTotal;
        this.order = order;
        this.product = product;
    }

    public OrderItemDto() {
        super();
    }

    @Override
    public String toString() {
        return "OrderItemDto{" +
               "quantity=" + quantity +
               ", unityPrice=" + unityPrice +
               ", subTotal=" + subTotal +
               ", orderId=" + (order != null ? order.getItems() : null) + //para evitar recursão infinita.
               ", productName=" + (product != null ? product.getName() : null) + //para evitar recursão infinita.
               '}';
    }
}
