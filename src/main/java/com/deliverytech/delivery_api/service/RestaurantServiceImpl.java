package com.deliverytech.delivery_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.dto.ClientDto;
import com.deliverytech.delivery_api.dto.ProductDto;
import com.deliverytech.delivery_api.dto.RestaurantDto;
import com.deliverytech.delivery_api.entity.Restaurant;
import com.deliverytech.delivery_api.repository.IRestaurantRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired //Indica que vai utilizar a ingestão de dependência
    private IRestaurantRepository repository; //atributo que referencia a interface criada
    
    public RestaurantServiceImpl(IRestaurantRepository restaurantRepository) {
        this.repository = restaurantRepository;
    }

    public RestaurantServiceImpl() {
        super();
    }

     public List<RestaurantDto> findAll() {
        return repository.findAll().stream().map(this::ConvertEntityToDTO).collect(Collectors.toList()); //Mapeia o resultado do método e retorna como lista
    }

    private RestaurantDto ConvertEntityToDTO(Restaurant entity){
        RestaurantDto dto = new RestaurantDto();
        dto.setName(entity.getName());
        dto.setDescription((entity.getDescription()));
        return dto;

    
    }

    @Override
    public Long cadastrarRestaurante(RestaurantDto restaurantDto) {
        ModelMapper modelMapper = new ModelMapper();
        Restaurant restaurant = modelMapper.map(restaurantDto,Restaurant.class);
        Restaurant restaurantSaved = repository.save(restaurant);
        return restaurantSaved.getId();
    }

    @Override
    public RestaurantDto atualizarRestaurante(Long id, RestaurantDto restaurantDto) {
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado com ID: " + id));
        
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setPhoneNumber(restaurantDto.getPhoneNumber());
        restaurant.setCategory(restaurantDto.getCategory());
        restaurant.setDeliveryFee(restaurantDto.getDeliveryFee());
        restaurant.setDescription(restaurantDto.getDescription());
        restaurant.setActive(restaurantDto.getActive());
        restaurant.setRating(restaurantDto.getRating());
        
        repository.save(restaurant);
        return restaurantDto;
    }

    @Override
    public List<RestaurantDto> findByCategoria(String category) {
        ModelMapper modelMapper = new ModelMapper();        
        return repository.findByCategoria(category).stream().map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RestaurantDto> findByNome(String name) {
        ModelMapper modelMapper = new ModelMapper();        
        return repository.findByNome(name).stream().map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RestaurantDto> findByAtivoTrue() {
        ModelMapper modelMapper = new ModelMapper();        
        return repository.findByAtivoTrue().stream().map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class)).collect(Collectors.toList());
    }

    
}

