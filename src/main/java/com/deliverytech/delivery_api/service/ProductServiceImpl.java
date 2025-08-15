package com.deliverytech.delivery_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deliverytech.delivery_api.dto.ProductDto;
import com.deliverytech.delivery_api.entity.Product;
import com.deliverytech.delivery_api.repository.IProductRepository;

import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;

@Service
public class ProductServiceImpl implements  ProductService{

    @Autowired //Indica que vai utilizar a ingestão de dependência
    private IProductRepository repository; //atributo que referencia a interface criada

    public ProductServiceImpl(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long cadastrarProduto(ProductDto productDto) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDto, Product.class);
        Product productSaved = repository.save(product);
        return productSaved.getId();
    }

    @Override
    public List<ProductDto> buscarProdutosPorRestaurante(Long restaurantId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarProdutosPorRestaurante'");
    }

    @Override
    public ProductDto buscarProdutoPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarProdutoPorId'");
    }

    @Override
    public ProductDto atualizarProduto(Long id, ProductDto productDto) {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setIsAvailable(productDto.getIsAvailable());
        repository.save(product);
        return productDto;
    }

    @Override
    public ProductDto buscarProdutosPorCategoria(String categoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarProdutosPorCategoria'");
    }

    @Override
    public ProductDto alterarDisponibilidade(Long id, Boolean isAvalible) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterarDisponibilidade'");
    }





}
