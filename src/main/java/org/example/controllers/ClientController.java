package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.core.Client;
import org.example.dtos.ClientDto;
import org.example.mappers.ClientMapper;
import org.example.services.clientservice.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientMapper.toDtoList(clientService.getAllClients()));
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto) {
        Client addedClient = clientService.addClient(clientMapper.toEntity(clientDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(clientMapper.toDto(addedClient));
    }

}
