package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.ClientLoan;

import java.util.List;

public interface ClientLoanService {
    List<ClientLoan> getClientLoan();
    ClientLoan getClientLoanId(Long id);
    ClientLoan saveClientLoan(ClientLoan clientLoan);
}
