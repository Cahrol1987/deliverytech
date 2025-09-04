package com.deliverytech.delivery_api.repository;

import java.util.List;

import com.deliverytech.delivery_api.dto.ClientDto;
import com.deliverytech.delivery_api.entity.Client;


public interface IClientRepositoryCustom {
    public List<Client> buscarClienteAtivo();
    public ClientDto buscarClientePorEmail(String email);
}
