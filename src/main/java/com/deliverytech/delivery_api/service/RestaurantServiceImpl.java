package com.deliverytech.delivery_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.dto.ProductDto;
import com.deliverytech.delivery_api.dto.RestaurantDto;
import com.deliverytech.delivery_api.entity.Restaurant;
import com.deliverytech.delivery_api.repository.IRestaurantRepository;

@Service
public class RestaurantServiceImpl {
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
}

