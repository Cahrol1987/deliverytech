package com.deliverytech.delivery_api.service;

import java.time.LocalDateTime;
import java.util.List;
import com.deliverytech.delivery_api.dto.OrderDto;
import com.deliverytech.delivery_api.entity.StatusOrder;

public interface OrderService {

   public OrderDto criarPedido(OrderDto orderDto);

   public List <OrderDto> findOrderByClientId(Long id); 
   public List <OrderDto> findOrderByStatus(StatusOrder status);
   public List<OrderDto> findOrdersByPeriod(LocalDateTime startDate, LocalDateTime endDate);
   public List<OrderDto> findLastTenOrders();
   public OrderDto atualizarStatus(Long id, StatusOrder novoStatus);

}
