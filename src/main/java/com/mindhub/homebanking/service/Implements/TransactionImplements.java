package com.mindhub.homebanking.service.Implements;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.TransactioRepository;
import com.mindhub.homebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionImplements implements TransactionService {
    @Autowired
    private TransactioRepository transactionRepository;

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
