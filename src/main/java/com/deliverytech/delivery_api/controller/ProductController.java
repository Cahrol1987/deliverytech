package com.deliverytech.delivery_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.deliverytech.delivery_api.dto.ProductDto;
import com.deliverytech.delivery_api.dto.RestaurantDto;
import com.deliverytech.delivery_api.entity.Product;
import com.deliverytech.delivery_api.service.ProductService;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/Product")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @RequestMapping("/findAll")
    @GetMapping
    public List<ProductDto> getAllProducts() {
        //return productService.findAll();
        return null;
    }

    @PostMapping
    public ResponseEntity<Long> cadastrarProduto(@RequestBody ProductDto productDto) {
        Long id = productService.cadastrarProduto(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }


    @PatchMapping("/{id}/Product")
    public ResponseEntity<ProductDto> atualizarProduto(@RequestBody ProductDto productDto, Long id) {
        ProductDto produtoSalvo = productService.atualizarProduto(id, productDto);
        return ResponseEntity.ok(produtoSalvo);
    }

    
    @GetMapping("/ByCategory")
    public ResponseEntity<List<ProductDto>> findProductByCategory(@RequestParam("query") String category) {
       List <ProductDto> productDto = productService.findProductByCategory(category);
        return ResponseEntity.ok(productDto);
    }
   
    @GetMapping("/Active")
        public ResponseEntity<List<ProductDto>> findByisAvailableTrue() {
        List<ProductDto> productDto = productService.findByisAvailableTrue();
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/ByRestaurantId")
        public ResponseEntity<List<ProductDto>> findByRestauranteId (@RequestParam("query") Long id) {
        List <ProductDto> productDto = productService.findByRestauranteId(id);
        return ResponseEntity.ok(productDto);
    }
}

