package com.deliverytech.delivery_api.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")

public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    // caso o nome da coluna no banco tenha outro nome devo apontar dessa forma antes da declaração da variável: @Colunm(name = "descricao_local")
    private String description; 
    @OneToMany (mappedBy = "restaurant")
    private List <Product> products;
}
