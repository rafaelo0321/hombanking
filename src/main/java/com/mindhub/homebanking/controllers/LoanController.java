package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTO.CreateLoanDTO;
import com.mindhub.homebanking.DTO.LoanApplicationDTO;
import com.mindhub.homebanking.DTO.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.service.AccountService;
import com.mindhub.homebanking.service.ClientLoanService;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


@RestController//Escucha y responde peticiones con las restricciones de REST
@RequestMapping("/api")
public class LoanController {
    @Autowired
    private LoanService loanService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactioRepository transactionRepository;
    @Autowired
    private ClientLoanService clientLoanService;

    @GetMapping("/loans")
    public Set<LoanDTO> getLoanDTO(){

        return loanService.getAllLoan().stream().map(LoanDTO::new).collect(Collectors.toSet());
    }
    @GetMapping("/loans/{id}")
    public LoanDTO getLoanDTO(@PathVariable Long id){

        return new LoanDTO(loanService.getByLoanId(id));

    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> createLoans(
            @NonNull Authentication authentication,
            @RequestBody LoanApplicationDTO loanApplicationDTO) {


        Client clientAuthentication = clientService.findClientEmailAddress(authentication.getName());
        //Trae la cuenta de destino.
        Account accountDestino = accountService.findByNumberAccount(loanApplicationDTO.getNumberCuentaDestino());
        //Concordar los datos del cliente que el cliente excista.
        //Error: Sobre escribir el meto en el repositorio y pasar los parametro del objeto entrante.
        Loan loan = loanService.findByIdLoan(loanApplicationDTO.getId());


        //Solicitud de prestamo sumandole los intereces
        double solicitudPrestamo = loanApplicationDTO.getAmount() * loan.getLoanInterest();

        String description = loan.getName() + " loan approved";
        //Verica que los datos ingresados no esten vacios
        if(loanApplicationDTO.getNumberCuentaDestino().isEmpty()
                ||loanApplicationDTO.getAmount() == null
                || loanApplicationDTO.getPayments() == null) {
            return new ResponseEntity<Object>("Please verify that the data is not empty",HttpStatus.FORBIDDEN);
        }
        if(loanApplicationDTO.getAmount() < 0) {
            return new ResponseEntity<Object>("The value of the loan must be greater than 0",HttpStatus.FORBIDDEN);
        }

        //Verificar que el préstamo exista
        if (loan.getId() == null){
            return new ResponseEntity<Object>("Verify that the loan exists",HttpStatus.FORBIDDEN);
        }
        //Verificar que el monto solicitado no exceda el monto máximo del préstamo
        if (loan.getMaxAmount() < loanApplicationDTO.getAmount()){
            return new ResponseEntity<Object>("Verify that the requested amount does not exceed the maximum loan amount",HttpStatus.FORBIDDEN);
        }

        // Verifica que la cuenta de destino le pertenesca el cliente con la section id autenticada
        if (!clientAuthentication.getAccounts().contains(accountDestino)) {
            return new ResponseEntity<Object>("Verify that the destination account belongs to the authenticated client",HttpStatus.FORBIDDEN);
        }
        //Evita que se soliciten varias veces el mismo prestamo.
        if(clientAuthentication.getClientLoans().stream().filter(loans -> loans.getId().equals(loanApplicationDTO.getId())).count() > 0){

            return new ResponseEntity<Object>("You cannot apply for the same loan more than twice",HttpStatus.FORBIDDEN);

        }

        // Verifica que la cantidad de cuotas se encuentre entre las disponibles del préstamo
        for (int i = 0; i <= loan.getPaymentsLoans().size(); i++) {

            if (!loan.getPaymentsLoans().contains(loanApplicationDTO.getPayments())) {

                return new ResponseEntity<Object>("Verify that the number of installments is among those available for the loan",HttpStatus.FORBIDDEN);

            }
        }

        Transaction CREDIT = new Transaction(TransactionType.CREDITO, description,loanApplicationDTO.getAmount(), LocalDateTime.now() ,accountDestino);
        transactionRepository.save(CREDIT);
        accountDestino.setBalance(accountDestino.getBalance() + loanApplicationDTO.getAmount());

        ClientLoan loanPreaprovado = new ClientLoan(solicitudPrestamo, loanApplicationDTO.getPayments(), clientAuthentication,loan);

        clientLoanService.saveClientLoan(loanPreaprovado);
        accountService.saveAccount(accountDestino);

        return new ResponseEntity<Object>(" A loan has been created "+loanPreaprovado,HttpStatus.CREATED);



    }
    @PostMapping("/admin/crateLoan")
    public ResponseEntity<Object> createLoans(@RequestBody CreateLoanDTO createLoan){
        if (createLoan.getName().equals("") ||
                createLoan.getLoanInterest() == null ||
                createLoan.getPaymentsLoans() == null ||
                createLoan.getMaxAmount() == null ){
            return new ResponseEntity<Object>("hay campos nulos o vacios", HttpStatus.FORBIDDEN);
        }
        //Loan loans = loanService.findByNameLoan(createLoan.getName());

        Loan newLoan = new Loan(createLoan.getName(),createLoan.getMaxAmount(),createLoan.getPaymentsLoans(),createLoan.getLoanInterest());
        loanService.saveLoan(newLoan);
        return new ResponseEntity<Object>(newLoan,HttpStatus.CREATED);
    }

}
