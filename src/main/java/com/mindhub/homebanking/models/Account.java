package com.mindhub.homebanking.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// Definition
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    // Definition de las variables de type private
    // Note: El type of date of tha primary key is Long, por que el repository trabaja con este tipo de dato no importa si es primitivo o tipo dato base de datos, String lanza un error (255)
    private Long id;
    private String number;
    private LocalDateTime creationDate;
    private Double balance;

    private AccountType accountType;

    private Boolean switchAccount;

    //Creando la relacion entre la clase cliente y la clase cuanta

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="client_id")
    //Creando el tipo de tado cliente
    private Client client_id;



    // Definition de la relacion entre cuentas y transactions
    @OneToMany(
            mappedBy = "account_id",
            fetch = FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    //Relacion uno a uno entre tarjeta y cuenta
    @OneToOne(
            mappedBy = "account_card",
            fetch = FetchType.EAGER)
    private Card card_id;

    // Constructor vacio
    public Account() {
    }

    public Account(String number, LocalDateTime creationDate, Double balance, AccountType accountType, Boolean switchAccount) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.accountType = accountType;
        this.switchAccount = switchAccount;
    }

    public Account(String number, LocalDateTime creationDate, Double balance, AccountType accountType, Boolean switchAccount, Client client_id) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.accountType = accountType;
        this.switchAccount = switchAccount;
        this.client_id = client_id;
    }

    // Metodos get and set del document
    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getSwitchAccount() {
        return switchAccount;
    }

    public void setSwitchAccount(Boolean switchAccount) {
        this.switchAccount = switchAccount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    //Trasnforma el Json para ser un dato que se guarde en la base de datos y se pueda trabajar en java
    @JsonIgnore
    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Card  getCard_id() {
        return card_id;
    }

    public void setCard_id(Card card_id) {
        this.card_id = card_id;
    }


    //Se crea el metodo agregar transactions para poder agregar las transacciones cuando un cliente las realiza
    public void addTransaction(Transaction transaction) {
        transaction.setAccount_id(this);
        transactions.add(transaction);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", creationDate=" + creationDate +
                ", balance=" + balance +
                ", client_id=" + client_id +
                ", transactions=" + transactions +
                '}';
    }
    //final de la aplicación
}
