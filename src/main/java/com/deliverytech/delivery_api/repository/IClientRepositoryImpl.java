package com.deliverytech.delivery_api.repository;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery_api.dto.ClientDto;
import com.deliverytech.delivery_api.entity.Client;
import com.deliverytech.delivery_api.entity.Restaurant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class IClientRepositoryImpl implements IClientRepositoryCustom{
     @PersistenceContext //é o que permite executar as queries na nossa base de dados
    private EntityManager entityManager; //como eu acesso a base dedos
      
    @Override
    public List<Client> buscarClienteAtivo() {
        String formattedString = String.format("SELECT c FROM Client c WHERE c.active = true");
        TypedQuery<Client> query = entityManager.createQuery(formattedString, Client.class);     
        return query.getResultList();

    }

    @Override
    public ClientDto buscarClientePorEmail(String email) {
        TypedQuery<Client> query = entityManager.createQuery("SELECT p FROM Client p WHERE p.email = :email", Client.class);
        query.setParameter("email", email);

        Client client = query.getSingleResult();

        ClientDto clientDto = new ClientDto();
        clientDto.setEmail(client.getEmail());
        return clientDto;
    }
}


