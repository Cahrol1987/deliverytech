package com.deliverytech.delivery_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.dto.ClientDto;
import com.deliverytech.delivery_api.dto.RestaurantDto;
import com.deliverytech.delivery_api.entity.Client;
import com.deliverytech.delivery_api.repository.IClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired //indica que vamos utilizar a injestão de dependências

    private IClientRepository repository;

    public ClientServiceImpl(IClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long cadastrarCliente(ClientDto clientDto) {
        ModelMapper modelMapper = new ModelMapper();
        Client client = modelMapper.map(clientDto,Client.class);
        Client productSaved = repository.save(client);
        return productSaved.getId();
    }

    // @Override
    // //@Transactional (readOnly = true)
    // public ClientDto buscarClientePorId(Long id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'buscarClientePorId'");
    //     //Client client = IClientRepository.findById.orElseThrow(()-> new EntityNotFoundException("Cliente não encontrado: "+id));
    //     //return modelMapper.map(client,ClientDto.class);
    // }

    // @Override
    // public ClientDto buscarClientePorEmail(String email) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'buscarClientePorEmail'");
    // }

    @Override
    public ClientDto atualizarCliente(Long id, ClientDto clientDto) {
        Client client = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
        
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setAddress(clientDto.getAddress());
        client.setActive(clientDto.getActive());

        repository.save(client);
        return clientDto;
    }

    @Override
    public ClientDto buscarClientePorEmail(String email) {
        // ModelMapper modelMapper = new ModelMapper();        
        // Client client = repository.buscarClientePorEmail(email); // retorna Client
        // return modelMapper.map(client, ClientDto.class);
        return repository.buscarClientePorEmail(email);
    }

    @Override
    public List<ClientDto> buscarClienteAtivo() {
        ModelMapper modelMapper = new ModelMapper();        
        return repository.buscarClienteAtivo().stream().map(client -> modelMapper.map(client, ClientDto.class)).collect(Collectors.toList());
    }

}