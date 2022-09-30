package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    //parametro de la clase clietn
    private Long idClient;
    private String nameClient,lastNameClient,emailAddressClient;
    private Set<AccountDTO> accounts;
    private Set<ClientLoanDTO> loanClients;
    private Set<CardDTO> cards;
    public ClientDTO(){}
    //Sirve para mostrar y recibir datos de la forma en que yo lo desee segun la necesidad
    public ClientDTO(Client client){
        //Se ingualan los atributos con los gutters de client para poder llamarlos en esta clase.
        // Que se estan recibiendo por parametro
        this.idClient = client.getId();
        this.nameClient = client.getName();
        this.lastNameClient = client.getLastName();
        this.emailAddressClient = client.getEmailAddress();


        this.loanClients = client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
        //Error: no se habia declarodo el metodo getClientLoans en la clase client
        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
        this.cards = client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());
        //Convierte una lista a un string. se mapea para que se convierta en el objeto DTO

    }

    public Long getIdClient() {
        return idClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public String getLastNameClient() {
        return lastNameClient;
    }

    public String getEmailAddressClient() {
        return emailAddressClient;
    }


    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public Set<ClientLoanDTO> getLoanClients() {
        return loanClients;
    }
    public Set<CardDTO> getCards() {
        return cards;
    }

}
