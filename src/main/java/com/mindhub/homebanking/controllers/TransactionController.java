package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.TransactioRepository;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
// Repasar modelo asid

//Los servlet son los encargados de escuchar y responden a travez del controlador. Son los servicios del controlador.
@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;
    //Se accede a la transaction en general.
    @PostMapping("/transactions")
    @Transactional //Revierte una operacion a su estado original, en caso de algun problema, Corrobora que todo este bien.
    public ResponseEntity<Object> createTransaction(
            Authentication authentication,

            @RequestParam Double amount,
            @RequestParam String description,
            @RequestParam String originNumber,
            @RequestParam String destinyNumber) {

        Client clientAuthentication = clientService.findClientEmailAddress(authentication.getName());

        Account accountOrigin = accountService.findByNumberAccount(originNumber);
        Account accountDestiny = accountService.findByNumberAccount(destinyNumber);
        //Buscan las cuentas por su número sí estas existen o no.

        if ( amount == null || description.isEmpty() || originNumber.isEmpty() || destinyNumber.isEmpty()) {

            return new ResponseEntity<>("You did not fill all the fields of the form",HttpStatus.FORBIDDEN);
        }

        if (accountOrigin == accountDestiny ){
            return new ResponseEntity<>("The numbers are the same, please check the account numbers",HttpStatus.FORBIDDEN);
        }
        if (accountOrigin == null){
            return new ResponseEntity<>("Verify that the source account exists",HttpStatus.FORBIDDEN);
        }
        if (accountDestiny == null){
            return new ResponseEntity<>("Verify that the source account exists",HttpStatus.FORBIDDEN);
        }
        if (accountOrigin.getBalance() <= amount){
            return new ResponseEntity<>("Verify that your account has the necessary amount for this transaction" ,HttpStatus.FORBIDDEN);
        }
        //Verificar que la cuenta de origen pertenezca al cliente autenticado

        //Nota de error ocurrido y solucionado: Se ingresó el numero de cuenta origen tipo String en vez de la cuenta de origen
        // de tipo Objeto, lo cual solicitaba que fuera un
        // set de cuentas, para funsionar, al cambiar el numero de cuenta de origen por la cuenta de origen se solucionó el error,
        // y comenzó a funsionar correctamente
        // al estar la creacion de la cuenta en el condicional, no se puede negar la condicion si no dejarla en positivo o en verdadero para que esta
        // pueda funsionar correctamente.
        if (clientAuthentication.getAccounts().contains(accountOrigin)) {

            description += " " + amount + " from " + originNumber + " to " + destinyNumber;

            Transaction newTransactionCredit = new Transaction(TransactionType.CREDITO, description, amount, LocalDateTime.now(), accountDestiny);
            accountOrigin.setBalance(accountOrigin.getBalance() - amount);
            newTransactionCredit.setShowTransaction(true);

            Transaction newTransactionDebit = new Transaction(TransactionType.DEBITO, description, amount, LocalDateTime.now(), accountOrigin);
            accountDestiny.setBalance(accountDestiny.getBalance() + amount);
            newTransactionDebit.setShowTransaction(true);

            newTransactionDebit.setAmountPost(accountOrigin.getBalance());
            newTransactionCredit.setAmountPost(accountDestiny.getBalance());

            transactionService.saveTransaction(newTransactionCredit);
            transactionService.saveTransaction(newTransactionDebit);

            accountService.saveAccount(accountDestiny);
            accountService.saveAccount(accountOrigin);

            return new ResponseEntity<>((
                    " Creditos Debitados " + newTransactionDebit
                    + " Creditos asignados " + newTransactionCredit),
                    HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Verify el number account origin",HttpStatus.FORBIDDEN);
        }

    }
    @Autowired
    TransactioRepository listTransactions;
    @PostMapping("/transactions/1")
    public ResponseEntity<List<Transaction>> listTransactios(Authentication authentication,

                                                             @RequestParam LocalDateTime start,
                                                             @RequestParam LocalDateTime end) {
        Client client = clientService.findClientEmailAddress(authentication.getName());

        Transaction transaction = listTransactions.findByCreationDateBetween(start, end);

        return new ResponseEntity<List<Transaction>>(HttpStatus.OK);

    }
}