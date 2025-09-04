package com.deliverytech.delivery_api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.deliverytech.delivery_api.dto.ClientDto;

@Service
public interface ClientService {
    public Long cadastrarCliente(ClientDto clientDto);
    public List<ClientDto> buscarClienteAtivo();
    public ClientDto buscarClientePorEmail(String email);
    public ClientDto atualizarCliente(Long id, ClientDto clientDto);

}
