package org.example.services.clientservice;

import org.example.core.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();

    Client addClient(Client client);

    Client updateClient(Client client, long id);
}
