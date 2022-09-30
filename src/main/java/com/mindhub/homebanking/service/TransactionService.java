package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions();
    Transaction saveTransaction(Transaction transaction);
}
