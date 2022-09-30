
package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTO.ClientLoanDTO;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.service.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController//Escucha y responde peticiones con las restricciones de REST
@RequestMapping("/api")
public class ClientLoanController {
    @Autowired
    private ClientLoanService loanService;

    @GetMapping("/clientLoans")
    public Set<ClientLoanDTO> getClientLoanDTO(){

        return loanService.getClientLoan().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
    }
    @GetMapping("/clientLoans/{id}")
    public ClientLoanDTO getClientLoanDTO(@PathVariable Long id){

        return new ClientLoanDTO(loanService.getClientLoanId(id));

    }
}
