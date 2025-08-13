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
    private RestaurantDto restaurant;
    private List<OrderItemDto> items;
    private ClientDto client;
    private StatusOrder status;

    public OrderDto(LocalDateTime orderDate, String addressDelivery, BigDecimal subTotal, BigDecimal deliveryTax, BigDecimal totalValue, RestaurantDto restaurant, List<OrderItemDto> items, ClientDto client, StatusOrder status) {
        this.orderDate = orderDate;
        this.addressDelivery = addressDelivery;
        this.subTotal = subTotal;
        this.deliveryTax = deliveryTax;
        this.totalValue = totalValue;
        this.restaurant = restaurant;
        this.items = items;
        this.client = client;
        this.status = status;
    }

    public OrderDto() {
        super();
    }

    @Override
    public String toString() {
        return "OrderDto{" +
               "orderDate=" + orderDate +
               ", addressDelivery='" + addressDelivery + '\'' +
               ", subTotal=" + subTotal +
               ", deliveryTax=" + deliveryTax +
               ", totalValue=" + totalValue +
               ", restaurantName=" + (restaurant != null ? restaurant.getName() : null) + //mostra apenas o nome para manter legível e seguro
               ", itemsCount=" + (items != null ? items.size() : 0) + //mostra apenas o nome para manter legível e seguro
               ", clientName=" + (client != null ? client.getName() : null) + //mostra apenas o nome para manter legível e seguro
               ", status=" + status +
               '}';
    }

}


