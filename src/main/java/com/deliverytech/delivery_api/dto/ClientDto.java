package com.deliverytech.delivery_api.dto;

import lombok.Data;

@Data
public class ClientDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private Boolean active;

}
