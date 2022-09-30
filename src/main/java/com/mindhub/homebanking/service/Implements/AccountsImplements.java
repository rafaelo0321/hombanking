package com.mindhub.homebanking.service.Implements;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsImplements implements AccountService {
    @Autowired
    //Un cableado para conectar los datos de la base de datos
    //con los datos que voy a necesita en el controlador.
    AccountRepository accountRepository;

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountId(Long accountId) {
        return accountRepository.findById(accountId).get();
    }

    public Account findByNumberAccount(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

}
