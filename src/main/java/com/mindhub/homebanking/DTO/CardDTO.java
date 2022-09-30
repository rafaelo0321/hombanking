package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.*;
import java.time.LocalDate;

public class CardDTO {
    private Long id;
    private String cardHolder;
    private CardType typeCard;
    private CardColor color;
    private String number;
    private Integer cvv;
    private LocalDate thruDate;
    private LocalDate fromDate;
    private Boolean showCard;

    /*private Client client;*/
    private Account account;

    public CardDTO() {
    }

    public CardDTO(Card card) {

        this.id = card.getId();
        this.cardHolder = card.getCardHolder();
        this.typeCard = card.getTypeCard();
        this.color = card.getColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.thruDate = card.getThruDate();
        this.fromDate = card.getFromDate();
        this.showCard = card.getSwitchCard();
        /*this.client = card.getClientCard();*/
        this.account = card.getAccount_card();


    }

    public Long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public CardType getTypeCard() {
        return typeCard;
    }

    public CardColor getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public Boolean getShowCard() {
        return showCard;
    }

   /* public Client getClient() {
        return client;
    }*/

    public Account getAccount() {
        return account;
    }
}
