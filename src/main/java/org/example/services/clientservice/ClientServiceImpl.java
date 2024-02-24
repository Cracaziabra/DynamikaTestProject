package org.example.services.clientservice;

import lombok.RequiredArgsConstructor;
import org.example.core.Client;
import org.example.exceptions.ClientNotFoundException;
import org.example.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.saveAndFlush(client);
    }

    @Override
    public Client updateClient(Client client, long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException(id);
        }
        client.setId(id);

        return clientRepository.saveAndFlush(client);
    }
}
