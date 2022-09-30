package com.mindhub.homebanking.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;


// Definition
@Entity //Crea un a tabla en la base de datos.
public class Card {

    @Id //Clase primaria
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native") //Genere el valor del id, de forma automatica y nativa
    @GenericGenerator(name = "native", strategy = "native") //Se verifica que no se repita Investigar cada anotacion.

    // Definition de las variables de type private
    // Note: El type of date of tha primary key is Long, por que el repository trabaja con este tipo de dato
    // no importa si es primitivo o tipo dato base de datos, String lanza un error (255)
    private Long id;
    private String cardHolder;
    private CardType typeCard;
    private CardColor color;
    private String number;
    private Integer cvv;
    private LocalDate thruDate;
    private LocalDate fromDate;
    private Boolean switchCard;
    //Creando la relacion entre la clase cliente y la clase cuanta

    //Creando el tipo de tado cliente
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="clientCard")
    private Client clientCard;
    //Creando la relacion card account.
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="account_card")
    private Account account_card;

    // Constructor vacio
    public Card() {
    }
    // Constructor con todas las variables asignadas a la class


    public Card(String cardHolder, CardType typeCard, CardColor color, String number, Integer cvv, LocalDate thruDate, LocalDate fromDate, Boolean switchCard) {
        this.cardHolder = cardHolder;
        this.typeCard = typeCard;
        this.color = color;
        this.number = number;
        this.cvv = cvv;
        this.thruDate = thruDate;
        this.fromDate = fromDate;
        this.switchCard = switchCard;
    }

    public Card(String cardHolder, CardType typeCard, CardColor color, String number, Integer cvv, LocalDate thruDate, LocalDate fromDate, Boolean switchCard, Account account_card) {
        this.cardHolder = cardHolder;
        this.typeCard = typeCard;
        this.color = color;
        this.number = number;
        this.cvv = cvv;
        this.thruDate = thruDate;
        this.fromDate = fromDate;
        this.switchCard = switchCard;
        this.account_card = account_card;
    }

    public Card(String cardHolder, CardType typeCard, CardColor color, String number, Integer cvv, LocalDate thruDate, LocalDate fromDate, Boolean switchCard, Client clientCard) {
        this.cardHolder = cardHolder;
        this.typeCard = typeCard;
        this.color = color;
        this.number = number;
        this.cvv = cvv;
        this.thruDate = thruDate;
        this.fromDate = fromDate;
        this.switchCard = switchCard;
        this.clientCard = clientCard;
    }

    // Metodos get and set del document
    public Long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public CardType getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(CardType typeCard) {
        this.typeCard = typeCard;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public Boolean getSwitchCard() {
        return switchCard;
    }

    public void setSwitchCard(Boolean switchCard) {
        this.switchCard = switchCard;
    }

    //Trasnforma el Json para ser un dato que se guarde en la base de datos y se pueda trabajar en java
    @JsonIgnore
    public Account getAccount_card() {
        return account_card;
    }

    public void setAccount_card(Account account_card) {
        this.account_card = account_card;
    }

    @JsonIgnore
    public Client getClientCard() {
        return clientCard;
    }

    public void setClientCard(Client clientCard) {
        this.clientCard = clientCard;
    }
    //final de la aplicación
}
