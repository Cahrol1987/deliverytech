package com.deliverytech.delivery_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.deliverytech.delivery_api.dto.ClientDto;
import com.deliverytech.delivery_api.service.ClientService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/Client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Long> cadastrarCliente(@RequestBody ClientDto clientDto) {
        Long id = clientService.cadastrarCliente(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PatchMapping("/{id}/Client")
    public ResponseEntity<ClientDto> atualizarCliente(@RequestBody ClientDto clientDto, Long id) {
        ClientDto clienteSalvo = clientService.atualizarCliente(id, clientDto);
        return ResponseEntity.ok(clienteSalvo);
    }

    @GetMapping("/ByEmail")
    public ResponseEntity <ClientDto> buscarClientePorEmail(@RequestParam("query") String email) {
        ClientDto clientDto = clientService.buscarClientePorEmail(email);
        return ResponseEntity.ok(clientDto);
    }

    @GetMapping("/Active")
    public ResponseEntity<List<ClientDto>> buscarClienteAtivo() {
        List<ClientDto> clientDto = clientService.buscarClienteAtivo();
        return ResponseEntity.ok(clientDto);
    }

}
