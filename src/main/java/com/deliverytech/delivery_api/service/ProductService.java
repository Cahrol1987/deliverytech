package com.deliverytech.delivery_api.service;

import java.util.List;

import com.deliverytech.delivery_api.dto.ProductDto;
import com.deliverytech.delivery_api.entity.Product;

public interface ProductService {
    public Long cadastrarProduto(ProductDto productDto);
    public List <ProductDto> buscarProdutosPorRestaurante(Long restaurantId);
    public ProductDto buscarProdutoPorId(Long id);
    public ProductDto atualizarProduto(Long id, ProductDto productDto);
    public ProductDto alterarDisponibilidade(Long id, Boolean isAvalible);
    public List <ProductDto> findProductByCategory(String category);
    public List<ProductDto> findByisAvailableTrue();
    public List<ProductDto> findByRestauranteId(Long id);
    
}
