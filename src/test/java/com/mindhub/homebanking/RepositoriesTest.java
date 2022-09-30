package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.service.CardService;
import com.mindhub.homebanking.service.LoanService;
import com.mindhub.homebanking.utils.CardUtils;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
//Las anotacione abajo indican que se debe escanear en buscar clases Entity y configurar los repositocios JPA
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoriesTest {
    //Se busca saber si la conexion de la base de datos con la aplicación son correctas.
    @Autowired
    LoanRepository repositoryLoan;
    //Las pruebas que se estan realizando

    @Test
    public void existLoans(){
        //Know created a methods public that verifier to exist the loans.
        //
        List<Loan> loans = repositoryLoan.findAll();
        assertThat(loans,is(not(empty())));
    }

    @Test
     public void existMortgageLoan(){
        //Verifier que hay uno say personnel

        List<Loan> loans = repositoryLoan.findAll();
        assertThat(loans,hasItem(hasProperty("name",is("Mortgage"))));

    }

    @Test
    public void cardNumberlsCreated(){

        String cardNumber = CardUtils.getNumber();
        assertThat(cardNumber,is(not(emptyOrNullString())));

    }
    @Autowired
    AccountRepository accountRepository;
    @Test
    public void findByNumber() {

        //Un valor exitoso
        Account account = accountRepository.findByNumber("VIN00000002");
        //un valor a comparar.
        assertEquals("VIN00000002",account.getNumber());
    }
    @Test
    public void findByNumberTestDos() {

        //Un valor exitoso
        List<Account> account = accountRepository.findAll();
        //un valor a comparar.
        assertThat(account,hasItem(hasProperty("number",is("VIN00000002"))));
    }
    @Autowired
    ClientRepository clientRepository;

    @Test
    public void findByEmailAddress() {
        String emailAddress = "rafael@admin.com";
        Client client = clientRepository.findByEmailAddress(emailAddress);
        assertThat(client.getAccounts().size(), equalTo(0));

    }
    @Test
    public void findByIdClient() {
        long id = 1;
        Client client = clientRepository.findById(id).get();

        assertThat(client.getAccounts().size(), equalTo(2));

    }
    @Autowired
    TransactioRepository transactioRepository;
    @Test
    public void showTransaction() {
        List<Transaction> transactions = transactioRepository.findAll();
        assertEquals(6, transactions.size());

    }
    @Test
    public void showTransactionAccount(){
        long id = 1;
        Transaction transaction = transactioRepository.findById(id).get();
        assertThat(transaction.getDescription(),is(not(emptyOrNullString())));
    }
    @Autowired
    CardRepository cardService;
    @Test
    public void showCard() {
        Card card = cardService.findByNumber("1");
        assertThat(card, is(not(1455)));
    }
    @Test
    public void showCardList() {
        List<Card> cards = cardService.findAll();
        assertThat(cards.size(), allOf(is(greaterThan(2)), is(lessThan(5))));
    }

}
