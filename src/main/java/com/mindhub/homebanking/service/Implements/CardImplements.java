package com.mindhub.homebanking.service.Implements;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardImplements implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardId(Long cardId) {
        return cardRepository.findById(cardId).get();
    }

    @Override
    public Card getCardIdDelete(Long cardId) {
        return null;
    }

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findByNumberCard(String number) {
        return cardRepository.findByNumber(number);
    }
}
