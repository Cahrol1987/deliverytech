package com.deliverytech.delivery_api.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.deliverytech.delivery_api.dto.OrderDto;
import com.deliverytech.delivery_api.dto.OrderItemDto;
import com.deliverytech.delivery_api.dto.ProductDto;
import com.deliverytech.delivery_api.entity.Client;
import com.deliverytech.delivery_api.entity.OrderItem;
import com.deliverytech.delivery_api.entity.Orders;
import com.deliverytech.delivery_api.entity.Product;
import com.deliverytech.delivery_api.entity.Restaurant;
import com.deliverytech.delivery_api.entity.StatusOrder;
import com.deliverytech.delivery_api.repository.IClientRepository;
import com.deliverytech.delivery_api.repository.IOrderRepository;
import com.deliverytech.delivery_api.repository.IProductRepository;
import com.deliverytech.delivery_api.repository.IRestaurantRepository;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final IOrderRepository pedidoRepository;
    private final IClientRepository clientRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IProductRepository productRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(
            IOrderRepository pedidoRepository,
            IClientRepository clientRepository,
            IRestaurantRepository restaurantRepository,
            IProductRepository productRepository,
            ModelMapper modelMapper) {
        this.pedidoRepository = pedidoRepository;
        this.clientRepository = clientRepository;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDto criarPedido(OrderDto orderDto) {
        // 1. Validar cliente existe e está ativo
        Client client = clientRepository.findById(orderDto.getClient_id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        //if (!client.isAtivo()) {
        //    throw new BusinessException("Cliente inativo não pode fazer pedidos");
        //}

        // 2. Validar restaurante existe e está ativo
        Restaurant restaurant = restaurantRepository.findById(orderDto.getRestaurant_id())
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));

        // if (!restaurant.isAtivo()) {
        //     throw new BusinessException("Restaurante não está disponível");
        // }

        // 3. Validar produtos e calcular subtotal
        List<OrderItem> itensPedido = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;

        for (OrderItemDto itemDTO : orderDto.getItems()) {
            Product product = productRepository.findById(itemDTO.getProduct_id())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + itemDTO.getProduct_id()));

            // if (!product.isDisponivel()) {
            //     throw new BusinessException("Produto indisponível: " + product.getNome());
            // }

            // if (!product.getRestaurante().getId().equals(orderDto.getRestauranteId())) {
            //     throw new BusinessException("Produto não pertence ao restaurante selecionado");
            // }

            // Criar item do pedido
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setUnityPrice(product.getPrice());
            item.setSubTotal(product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));

            itensPedido.add(item);
            subtotal = subtotal.add(item.getSubTotal());
        }

        // 4. Calcular valor total do pedido
        BigDecimal taxaEntrega = restaurant.getDeliveryFee();
        BigDecimal valorTotal = subtotal.add(taxaEntrega);

        // 5. Criar e salvar pedido
        Orders pedido = new Orders();
        pedido.setClient(client);
        pedido.setRestaurant(restaurant);
        pedido.setOrderDate(LocalDateTime.now());
        pedido.setStatus(StatusOrder.PENDENTE);
        pedido.setAddressDelivery(orderDto.getAddressDelivery());
        pedido.setSubTotal(subtotal);
        pedido.setDeliveryTax(taxaEntrega);
        pedido.setTotalValue(valorTotal);

        // 6. Associar itens ao pedido
        itensPedido.forEach(item -> item.setOrders(pedido));
        pedido.setItems(itensPedido);

        // 7. Salvar pedido com itens
        Orders pedidoSalvo = pedidoRepository.save(pedido);

        // 8. Retornar DTO mapeado
        return modelMapper.map(pedidoSalvo, OrderDto.class);


    }

    @Override
    @Transactional
    public OrderDto atualizarStatus(Long id, StatusOrder novoStatus) {
        Orders pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

    pedido.setStatus(novoStatus);
    Orders pedidoAtualizado = pedidoRepository.save(pedido);

    return modelMapper.map(pedidoAtualizado, OrderDto.class);
    }
    
    @Override
    public List<OrderDto> findOrderByClientId(Long id) {
    ModelMapper modelMapper = new ModelMapper();        
    return pedidoRepository.findOrderByClientId(id).stream().map(orders -> modelMapper.map(orders, OrderDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findOrderByStatus(StatusOrder status) {
        ModelMapper modelMapper = new ModelMapper();        
        return pedidoRepository.findOrderByStatus(status).stream().map(orders -> modelMapper.map(orders, OrderDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findOrdersByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return pedidoRepository.findOrdersByPeriod(startDate, endDate).stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findLastTenOrders() {
        return pedidoRepository.findLastTenOrders().stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
    }

}