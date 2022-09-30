package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Card;

import java.util.List;

public interface CardService {
    public List<Card> getAllCards();

    Card getCardId(Long cardId);

    Card getCardIdDelete(Long cardId);

    Card saveCard(Card card);
    Card findByNumberCard(String number);

}
