package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTO.AccountDTO;
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
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mindhub.homebanking.utils.AccountUtils.getNumberAccount;
import static com.mindhub.homebanking.utils.CardUtils.*;

@RestController//Escucha y responde peticiones con las restricciones de REST Get,Post, patch, Put and Delete
public class AccountController {
    @Autowired //Un cableado para conectar los datos de la base de datos con los datos que voy a necesita en el controlador.
    private AccountService accountService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CardService  cardService;

    @GetMapping("/api/accounts")
    public Set<AccountDTO> getAccountsDTO() {

        return accountService.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
        //Devuelve lista de Account.Stream devuelve un str de account.
    }

    @GetMapping("/api/accounts/{id}")
    public AccountDTO getAccountDTO(@PathVariable Long id) {

        return new AccountDTO(accountService.getAccountId(id));
        //error No encontraba el cliente DTO porque se estaba nombrando el cliente DTO de forma correcta
        //Solucion
    }


    @PostMapping("/api/clients/current/accounts")//Se especifica que tipo de metodo es. si no se especifica el
    public ResponseEntity<Object> createAccount(Authentication authentication,
                                                @RequestParam AccountType accountType,
                                                @RequestParam CardColor cardColor
                                                ) { //Se toma como metodo Get.
        //Se crea un metodo de tipo response entity, el cual crea una cuenta
        //Un section ID, investigar sobre el section ID y la clase Autentication de Spring Boot.
        Client clientAuthentication = clientService.findClientEmailAddress(authentication.getName());

        int
            numberAccount = clientAuthentication.getAccounts().size();

        String number;

        // Comprobar numero de cuenta.

        do {
            number = getNumberAccount();

        } while (accountService.findByNumberAccount(number) != null);

        if (numberAccount >= 3) {
            return new ResponseEntity<>(
                    "you do not have the permissions to perform this action",
                    HttpStatus.FORBIDDEN);
        }
        Account accountCreate = new Account(number, LocalDateTime.now(), 0.0, accountType,true, clientAuthentication);
        accountService.saveAccount(accountCreate);
        //Creando la tarjeta.
        String cardHolder = getHolder(clientAuthentication);
        int aCvv = getCVV();
        String numberCard = getNumber();

        Card newCard = new Card(cardHolder,CardType.DEBITO,cardColor,numberCard,aCvv, LocalDate.now().plusYears(5),LocalDate.now(),true,accountCreate);

        cardService.saveCard(newCard);


        return new ResponseEntity<>(
                accountCreate,
                HttpStatus.CREATED);


    }
    @PatchMapping("api/accounts")
    public ResponseEntity<Object> ocularAccount(@RequestParam String number){
        Account account = accountService.findByNumberAccount(number);
        account.setSwitchAccount(false);

        accountService.saveAccount(account);
        return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
    }
}