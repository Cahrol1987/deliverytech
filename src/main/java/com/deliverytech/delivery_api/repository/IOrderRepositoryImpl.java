package com.deliverytech.delivery_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.deliverytech.delivery_api.dto.OrderDto;
import com.deliverytech.delivery_api.entity.Orders;
import com.deliverytech.delivery_api.entity.StatusOrder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class IOrderRepositoryImpl implements IOrderRepositoryCustom{
    @PersistenceContext //é o que permite executar as queries na nossa base de dados
    private EntityManager entityManager; //como eu acesso a base dedos

    @Override
    public List<Orders> findOrderByClientId(Long id) {
        String jpql = "SELECT o FROM Orders o WHERE o.client.id = :id";
        TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
        query.setParameter("id", id);  
        return query.getResultList();
    }


    @Override
    public List<Orders> findOrderByStatus(StatusOrder status) {
        String jpql = "SELECT o FROM Orders o WHERE o.status = :status";
        TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
        query.setParameter("status", status);  
        return query.getResultList();
    }

    @Override
    public List<Orders> findLastTenOrders() {
        String jpql = "SELECT o FROM Orders o ORDER BY o.orderDate DESC";
        TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
        query.setMaxResults(10); // pega só os 10 últimos
        return query.getResultList();
    }

    @Override
    public List<Orders> findOrdersByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        String jpql = "SELECT o FROM Orders o WHERE o.orderDate BETWEEN :startDate AND :endDate";
        TypedQuery<Orders> query = entityManager.createQuery(jpql, Orders.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

}
