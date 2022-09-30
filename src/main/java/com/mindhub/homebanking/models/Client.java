package com.mindhub.homebanking.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// Definition
@Entity
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")//Genera los valores de la clave primaria.
    //Los elementos strategy y generator en la anotación describen cómo se obtiene el valor generado.
    @GenericGenerator(name = "native", strategy = "native")//Evita la repeticion de valores o chequea que no se repitan los valores

    // Definition de las variables de type private
    // Note: El type of date of tha primary key is Long,
    // por que el repository trabaja con este tipo de dato no importa
    // si es primitivo o tipo dato base de datos, String lanza un error (255)
    private Long id;

    private String name,lastName,emailAddress,password;
    @OneToMany(mappedBy = "client_id", fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    // Creando la relacion entre la tabla padre y la tabla hijo (clientLoan
    @OneToMany(mappedBy = "clientLoan", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany(mappedBy = "clientCard", fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();

    // Constructor vacio
    public Client() {
    }
    // Constructor con todas las variables asignadas a la class
    public Client(String name, String lastName, String emailAddress,String password) {
        this.name = name;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    // Constructor con todas las variables asignadas a la class

    public Client(String name, String lastName, String emailAddress,String password, Set<Account> accounts, Set<ClientLoan> clientLoans, Set<Card> cards) {
        this.name = name;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.accounts = accounts;
        this.clientLoans = clientLoans;
        this.cards = cards;
    }

    public Client(String name, String lastName, String emailAddress, String password, Set<Account> accounts) {

        this.name = name;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.accounts = accounts;
    }

    // Metodos get and set del document
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    //Metodo para agregar cuentas a un cliente
    public Set<Account> getAccounts() {
        return accounts.stream().filter(account -> account.getSwitchAccount().equals(true)).collect(Collectors.toSet());
    }
    public void addAccount(Account account) {
        account.setClient_id(this);
        accounts.add(account);
    }
    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }
    public void addClientLoan(ClientLoan loanClient) {
        loanClient.setClientLoan(this);
        clientLoans.add(loanClient);
    }

    public Set<Card> getCards() {
        return cards.stream().filter(c -> c.getSwitchCard().equals(true)).collect(Collectors.toSet());
    }
    public void addCard(Card card) {
        card.setClientCard(this);
        cards.add(card);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                ", clientLoans=" + clientLoans +
                ", cards=" + cards +
                '}';
    }

    //final de la aplicación
}
