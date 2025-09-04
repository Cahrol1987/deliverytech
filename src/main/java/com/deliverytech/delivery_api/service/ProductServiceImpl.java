package com.deliverytech.delivery_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deliverytech.delivery_api.dto.ProductDto;
import com.deliverytech.delivery_api.entity.Product;
import com.deliverytech.delivery_api.entity.Restaurant;
import com.deliverytech.delivery_api.repository.IProductRepository;
import com.deliverytech.delivery_api.repository.IRestaurantRepository;

import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;

@Service
public class ProductServiceImpl implements  ProductService{

    @Autowired //Indica que vai utilizar a ingestão de dependência
    private IProductRepository repository; //atributo que referencia a interface criada

     @Autowired
    private IRestaurantRepository restaurantRepository;

    public ProductServiceImpl(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long cadastrarProduto(ProductDto productDto) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDto, Product.class);

        if (productDto.getRestaurant_id() != null) {
            Restaurant restaurant = restaurantRepository.findById(productDto.getRestaurant_id()).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
            product.setRestaurant(restaurant);
        }

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

        if (productDto.getRestaurant_id() != null) {
            Restaurant restaurant = restaurantRepository.findById(productDto.getRestaurant_id()).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
            product.setRestaurant(restaurant);
        }

        Product updatedProduct = repository.save(product);

        // Converte de volta para DTO
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(updatedProduct, ProductDto.class);

    }

    @Override
    public List <ProductDto> findProductByCategory(String category) {
        ModelMapper modelMapper = new ModelMapper();        
        return repository.findByCategory(category).stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDto alterarDisponibilidade(Long id, Boolean isAvalible) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterarDisponibilidade'");

    }

    @Override
    public List<ProductDto> findByisAvailableTrue() {
        ModelMapper modelMapper = new ModelMapper();        
        return repository.findByisAvailableTrue().stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByRestauranteId(Long id) {
        ModelMapper modelMapper = new ModelMapper();        
        return repository.findByRestauranteId(id).stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }





}
