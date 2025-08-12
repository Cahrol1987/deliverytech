package com.deliverytech.delivery_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "restaurant")

public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String name;
    // caso o nome da coluna no banco tenha outro nome devo apontar dessa forma antes da declaração da variável: @Colunm(name = "descricao_local")
    private String description; 


}
