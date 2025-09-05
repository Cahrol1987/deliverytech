package com.deliverytech.delivery_api.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.dto.OrderDto;
import com.deliverytech.delivery_api.dto.StatusUpdateRequest;
import com.deliverytech.delivery_api.entity.StatusOrder;
import com.deliverytech.delivery_api.service.OrderService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/Orders")

public class OrderController {
    @Autowired
    private OrderService orderService; 
    
    @PostMapping
    public ResponseEntity<OrderDto> criarPedido(@RequestBody OrderDto orderDto) {
        OrderDto pedidoCriado = orderService.criarPedido(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDto> atualizarStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
        OrderDto pedidoAtualizado = orderService.atualizarStatus(id, request.getStatus());
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @GetMapping("/byClientId")
    public ResponseEntity<List<OrderDto>> findOrderByClientId (@RequestParam("query") Long id) {
        List <OrderDto> orderDto = orderService.findOrderByClientId(id);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/byStatus")
    public ResponseEntity<List<OrderDto>> findOrderByStatus(@RequestParam StatusOrder status) {
        List<OrderDto> pedidos = orderService.findOrderByStatus(status);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/byPeriod")
    public ResponseEntity<List<OrderDto>> findOrdersByPeriod(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

    LocalDateTime start = startDate.atStartOfDay();
    LocalDateTime end = endDate.atTime(23, 59, 59);

    List<OrderDto> pedidos = orderService.findOrdersByPeriod(start, end);

    return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/lastTenOrders")
    public ResponseEntity<List<OrderDto>> findLastTenOrders () {
        List <OrderDto> orderDto = orderService.findLastTenOrders();
        return ResponseEntity.ok(orderDto);
    }

}
