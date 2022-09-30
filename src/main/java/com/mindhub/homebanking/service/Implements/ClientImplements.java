package com.mindhub.homebanking.service.Implements;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientImplements implements ClientService {
    @Autowired
    ClientRepository clientRepository;


    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
       return clientRepository.findById(id).get();
    }

    @Override
    public Client findClientEmailAddress(String emailAddress) {
        return clientRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public void saveClient(Client client) {

        clientRepository.save(client);
    }


}
