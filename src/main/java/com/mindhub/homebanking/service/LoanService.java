package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Loan;

import java.util.List;

public interface LoanService {

    Loan findByNameLoan(String name);

    Loan findByIdLoan(long id);
    public List<Loan> getAllLoan();
    Loan getByLoanId(Long id);

    Loan saveLoan(Loan newLoan);
    Loan findByNameLoan(Loan newLoan);

}
