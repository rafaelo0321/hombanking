package com.mindhub.homebanking.service.Implements;

import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.service.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientLoanImplements implements ClientLoanService {
    @Autowired
    ClientLoanRepository clientLoanRepository;
    @Override
    public List<ClientLoan> getClientLoan() {
        return clientLoanRepository.findAll();
    }

    @Override
    public ClientLoan getClientLoanId(Long id) {
        return clientLoanRepository.findById(id).get();
    }

    @Override
    public ClientLoan saveClientLoan(ClientLoan clientLoan) {
        return clientLoanRepository.save(clientLoan);
    }
}
