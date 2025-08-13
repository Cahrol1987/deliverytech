package com.deliverytech.delivery_api.dto;

import java.util.List;
import lombok.Data;

@Data
public class ClientDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean active;
    private List<OrderDto> orders;

    public ClientDto(String name, String email, String phoneNumber, String address, boolean active, List<OrderDto> orders) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.active = active;
        this.orders = orders;
    }

    public ClientDto() {
        super();
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", active=" + active +
                ", ordersCount=" + (orders != null ? orders.size() : 0) + //mostra apenas a quantidade de pedidos e não a lista toda
                '}';
    }
}
