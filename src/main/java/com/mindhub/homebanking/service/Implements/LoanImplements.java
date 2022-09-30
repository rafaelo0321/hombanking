package com.mindhub.homebanking.service.Implements;

import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanImplements implements LoanService {
    @Autowired
    LoanRepository loanRepository;


    public Loan findByNameLoan(String name) {
        return loanRepository.findByName(name);
    }

    public Loan findByIdLoan(long id) {
        return loanRepository.findById(id);
    }

    @Override
    public List<Loan> getAllLoan() {
        return loanRepository.findAll();
    }

    @Override
    public Loan getByLoanId(Long id) {
        return loanRepository.findById(id).get();
    }

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan findByNameLoan(Loan newLoan) {
        return loanRepository.findByName(newLoan.getName());
    }
}
