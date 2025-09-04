package com.deliverytech.delivery_api.repository;

import java.util.List;
import org.springframework.stereotype.Repository;


import com.deliverytech.delivery_api.entity.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class IRestaurantRepositoryImpl implements IRestaurantRepositoryCustom {
    @PersistenceContext //é o que permite executar as queries na nossa base de dados
    private EntityManager entityManager; //como eu acesso a base dedos

    @Override
    public List<Restaurant> findByCategoria(String category) {
        String formattedString = String.format("SELECT p FROM Restaurant p WHERE p.category = '%s'", category);
        TypedQuery<Restaurant> query = entityManager.createQuery(formattedString, Restaurant.class);     
        return query.getResultList();
    }

    @Override
    public List<Restaurant> findByAtivoTrue() {
        String formattedString = String.format("SELECT p FROM Restaurant p WHERE p.active = true");
        TypedQuery<Restaurant> query = entityManager.createQuery(formattedString, Restaurant.class);     
        return query.getResultList();
    }

    @Override
    public List<Restaurant> findByNome(String name) {
        String formattedString = String.format("SELECT p FROM Restaurant p WHERE p.name = '%s'", name);
        TypedQuery<Restaurant> query = entityManager.createQuery(formattedString, Restaurant.class);     
        return query.getResultList();
    }

    // @Override
    // public List<Restaurant> findByRestauranteId(Long id) {
    //     String formattedString = String.format("SELECT p FROM Restaurant p WHERE p.id = '%s'", id);
    //     TypedQuery<Restaurant> query = entityManager.createQuery(formattedString, Restaurant.class);     
    //     return query.getResultList();
    // }


}
