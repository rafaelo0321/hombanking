package com.mindhub.homebanking.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

// Definition
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    // Definition de las variables de type private
    // Note: El type of date of tha primary key is Long,
    // por que el repository trabaja con este tipo de dato no importa si es primitivo
    // o tipo dato base de datos, String lanza un error (255)
    private Long id;
    private TransactionType typeTransaction;//tipo de dato enum variable de tipo objeto
    private String description;
    private Double amount;
    private Double amountPost;
    private LocalDateTime creationDate;
    private Boolean showTransaction;

    //Creando la relacion entre la clase cliente y la clase cuanta

    @ManyToOne(fetch = FetchType.EAGER)//que reserve un espacio en la momoria de la aplicación java
    @JoinColumn(name ="account_id")
    //Creando el tipo de tado cuenta
    private Account account_id;

    // Constructor vacio
    public Transaction() {
    }

    // Constructor con todas las variables asignadas a la class

    public Transaction(TransactionType typeTransaction, String description, Double amount,Double amountPost, LocalDateTime creationDate,Boolean showTransaction, Account account_id) {
        this.typeTransaction = typeTransaction;
        this.description = description;
        this.amount = amount;
        this.amountPost = amountPost;
        this.creationDate = creationDate;
        this.showTransaction = showTransaction;
        this.account_id = account_id;
    }
    public Transaction(TransactionType typeTransaction, String description, Double amount, LocalDateTime creationDate, Account account_id) {
        this.typeTransaction = typeTransaction;
        this.description = description;
        this.amount = amount;
        this.creationDate = creationDate;
        this.account_id = account_id;
    }

    // constructor con todos los datos menos cuenta

    public Transaction(TransactionType typeTransaction, String description, Double amount, LocalDateTime creationDate) {
        this.typeTransaction = typeTransaction;
        this.description = description;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    // Metodos get and set del document

    public Long getId() {
        return id;
    }

    public TransactionType getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TransactionType typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountPost() {
        return amountPost;
    }

    public void setAmountPost(Double amountPost) {
        this.amountPost = amountPost;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getShowTransaction() {
        return showTransaction;
    }

    public void setShowTransaction(Boolean showTransaction) {
        this.showTransaction = showTransaction;
    }

    //Trasnforma el Json para ser un dato que se guarde en la base de datos y se pueda trabajar en java
    @JsonIgnore
    public Account getAccount_id() {
        return account_id;
    }
    public void setAccount_id(Account account_id) {
        this.account_id = account_id;
    }


//final de la aplicación
}
