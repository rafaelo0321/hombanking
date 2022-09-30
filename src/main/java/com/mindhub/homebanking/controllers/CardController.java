package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTO.CardDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mindhub.homebanking.utils.CardUtils.*;


@RestController
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/api/cards")
    public Set<CardDTO> getCardsDTO(){
        return cardService.getAllCards().stream().map(CardDTO::new).collect(Collectors.toSet());
    }
    @GetMapping("/api/cards/{id}")
    public CardDTO getCardDTO(@PathVariable Long id){
        return new CardDTO(cardService.getCardId(id));
    }
    @DeleteMapping("/api/clients/current/delete")
    public CardDTO deleteCardDTO(@PathVariable Long id){

        return new CardDTO(cardService.getCardIdDelete(id));
    }

    @PostMapping("/api/clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication,
                                             @RequestParam CardType cardType,
                                             @RequestParam CardColor color) {
        //Se crea un metodo de tipo response entity, el cual crea una cuenta
        // se le pasan como parametros el cliente autenticado con la clase Authentication de Spring Boot

        Client clientAuthentication = clientService.findClientEmailAddress(authentication.getName());
        //Solicito el cliente autenticado con el metodo, de consulta que está en el repositorio.y este me devuelve el nombre.


        String cardHolder = getHolder(clientAuthentication);
        // Se genera el nombre del tarjetaviente con la combinacion del nombre y el apellido del mismo.
        int aCvv = getCVV();
        //Se generan variables de tipo entera para así crear los inicios y fin de la tarjeta.
        String number = getNumber();

        // si el color que pasa por parametro ya esta en alguna card o exciste en una card, no deja que se genere otra, card del mismo color.
        //Devuelve el largo de este filtro, si encuentra una es porque exciste una ya creada de ese color y tipo.

        if (clientAuthentication.getCards().stream().filter(card -> card.getColor().equals(color) && card.getTypeCard().equals(cardType)).count() > 0){

            return new ResponseEntity<>("you do not have the permissions to perform this action", HttpStatus.FORBIDDEN);
        }
        //Se realiza una llamada al cliente que se autenticó, se muestar la propiedad cards con lo que tiene y a esto se le realiza un Stream de la clase Stream
        // Luego se filtra por color y tipo, igualandolo a las respectivas entradas por el cliente y por ultimo que me devuelva el numero de veces
        // que esto está, y si es mayor a cero que me devueva una respuesta de autorizacion no permitido,
        if(clientAuthentication.getCards().size() >= 6){
            return new ResponseEntity<>("you do not have the permissions to perform this action", HttpStatus.FORBIDDEN);
        }
        Card newCard = new Card( cardHolder
                ,cardType
                ,color
                ,number
                ,aCvv
                ,LocalDate.now().plusYears(5)
                ,LocalDate.now()
                ,true,clientAuthentication);

        cardService.saveCard(newCard);

        return new ResponseEntity<>(newCard,HttpStatus.CREATED);

    }
    @PatchMapping("api/cards")
    public ResponseEntity<Object> updateCard(@RequestParam String number){
        Card card = cardService.findByNumberCard(number);
        card.setSwitchCard(false);
        cardService.saveCard(card);
        return new ResponseEntity<>(card,HttpStatus.ACCEPTED);
    }

}