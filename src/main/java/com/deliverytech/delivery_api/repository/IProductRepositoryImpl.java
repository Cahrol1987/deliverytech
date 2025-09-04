package com.deliverytech.delivery_api.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.deliverytech.delivery_api.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
    public class IProductRepositoryImpl implements IProductRepositoryCustom {
    @PersistenceContext //é o que permite executar as queries na nossa base de dados
    private EntityManager entityManager; //como eu acesso a base dedos

    @Override
    public List<Product> findByisAvailableTrue() {
        String formattedString = String.format("SELECT p FROM Product p WHERE p.isAvailable = true");
        TypedQuery<Product> query = entityManager.createQuery(formattedString, Product.class);     
        return query.getResultList();
    }

    @Override
    public List<Product> findByCategory(String category) {
      String formattedString = String.format("SELECT p FROM Product p WHERE p.category = '%s'", category);
      TypedQuery<Product> query = entityManager.createQuery(formattedString, Product.class);     
      return query.getResultList();
    }

    @Override
    public List<Product> findByPriceLessThanEqual(BigDecimal price) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'findByPriceLessThanEqual'");
        return null;
    }

    @Override
    public List<Product> findByRestauranteId(Long id) {
        String jpql = "SELECT p FROM Product p WHERE p.restaurant.id = :id";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("id", id);  
        return query.getResultList();
    }
}