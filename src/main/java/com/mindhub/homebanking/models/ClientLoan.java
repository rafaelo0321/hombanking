package com.mindhub.homebanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private Double Amount;
    private Integer Payment;

    //Anotaciones de prestamos entre la tabla de intercambio o asociada(ClientLoanService) y la tabla de padre(Client - cliente)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientLoan")
    private Client clientLoan;

    //Anotaciones de prestamos entre la tabla de intercambio o asociada(ClientLoanService)
    // y la tabla de padre(Loan - prestamos)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loanClient")
    private Loan loanClient;

    public ClientLoan() {
    }

    public ClientLoan(Double amount, Integer payment, Client clientLoan, Loan loanClient) {
        this.Amount = amount;
        this.Payment = payment;
        this.clientLoan = clientLoan;
        this.loanClient = loanClient;
    }



    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public Integer getPayment() {
        return Payment;
    }

    public void setPayment(Integer payment) {
        Payment = payment;
    }
    @JsonIgnore
    public Client getClientLoan() {
        return clientLoan;
    }

    public void setClientLoan(Client clientLoan) {
        this.clientLoan = clientLoan;
    }
    @JsonIgnore
    public Loan getLoanClient() {
        return loanClient;
    }

    public void setLoanClient(Loan loanClient) {
        this.loanClient = loanClient;
    }



}
