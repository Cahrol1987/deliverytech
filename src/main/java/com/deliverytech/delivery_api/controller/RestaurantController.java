package com.deliverytech.delivery_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.deliverytech.delivery_api.dto.RestaurantDto;
import com.deliverytech.delivery_api.service.RestaurantService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/Restaurant")
@RestController //Para saber que é uma controller
public class RestaurantController {
   @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Long> cadastrarRestaurante(@RequestBody RestaurantDto restaurantDto) {
        Long id = restaurantService.cadastrarRestaurante(restaurantDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PatchMapping("/{id}/Restaurant")
    public ResponseEntity<RestaurantDto> atualizarCliente(@RequestBody RestaurantDto restaurantDto, Long id) {
        RestaurantDto restauranteSalvo = restaurantService.atualizarRestaurante(id, restaurantDto);
        return ResponseEntity.ok(restauranteSalvo);
    }
 
    @GetMapping("/ByCategory")
    public ResponseEntity<List<RestaurantDto>> findByCategoria(@RequestParam("query") String category) {
        List <RestaurantDto> restaurantDto = restaurantService.findByCategoria(category);
        return ResponseEntity.ok(restaurantDto);
    }

    @GetMapping("/ByName")
        public ResponseEntity<List<RestaurantDto>> findByNome(@RequestParam("query") String name) {
        List <RestaurantDto> restaurantDto = restaurantService.findByNome(name);
        return ResponseEntity.ok(restaurantDto);
    }

    @GetMapping("/Active")
        public ResponseEntity<List<RestaurantDto>> findByAtivoTrue() {
        List<RestaurantDto> restaurantDto = restaurantService.findByAtivoTrue();
        return ResponseEntity.ok(restaurantDto);
    }

    // @RequestMapping("/api/restaurantes")
    // public List<RestaurantDto> listRestaurants() {
    //     //return restaurantService.findAll();
    //     return null;
    // } 
}


