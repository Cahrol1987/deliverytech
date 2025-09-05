package com.deliverytech.delivery_api.repository;

import java.math.BigDecimal;
import java.util.List;

import com.deliverytech.delivery_api.entity.Product;

public interface IProductRepositoryCustom {
    // Produtos por restaurante
    List<Product> findByRestauranteId(Long id);

    // Apenas produtos disponíveis
    List<Product> findByisAvailableTrue();

    // Produtos por categoria
    List<Product> findByCategory(String category);

    // Por faixa de preço (menor ou igual)
    List<Product> findByPriceLessThanEqual(BigDecimal price);
}
