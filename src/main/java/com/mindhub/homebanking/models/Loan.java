package com.mindhub.homebanking.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Definition
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    // Definition de las variables de type private
    // Note: El type of date of tha primary key is Long, por que el repository trabaja con este tipo de dato no importa si es primitivo o tipo dato base de datos, String lanza un error (255)
    private Long id;

    private String name;
    private Double maxAmount;
    @ElementCollection
    @Column(name = "paymentsLoan")
    private List<Integer> paymentsLoans;

    private Double loanInterest;

    // Creando la relacion entre la tabla padre y la tabla hijo (clientLoan
    @OneToMany(mappedBy = "loanClient", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();

    // Constructor vacio
    public Loan() {
    }
    // Constructor con todas las variables asignadas a la class

    public Loan(String name, Double maxAmount, List<Integer> paymentsLoans,Double loanInterest) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.paymentsLoans = paymentsLoans;
        this.loanInterest = loanInterest;

    }

    public Loan(String name, Double maxAmount, List<Integer> paymentsLoans,Double loanInterest, Set<ClientLoan> clientLoans) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.paymentsLoans = paymentsLoans;
        this.loanInterest = loanInterest;
        this.clientLoans = clientLoans;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPaymentsLoans() {
        return paymentsLoans;
    }

    public void setPaymentsLoans(List<Integer> paymentsLoans) {
        this.paymentsLoans = paymentsLoans;
    }

    public Double getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(Double loanInterest) {
        this.loanInterest = loanInterest;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    // Creando metodo para acceder a la vista de los prestamos

    public void addLoanClient(ClientLoan clientLoan) {
        clientLoan.setLoanClient(this);
        clientLoans.add(clientLoan);
    }

    //

//final de la aplicación
}
