package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Client;

import java.util.List;


public interface ClientService {

    public List<Client> getAllClient();
    Client getClientById(Long id);
    Client findClientEmailAddress(String emailAddress);

    void saveClient(Client client);

}
