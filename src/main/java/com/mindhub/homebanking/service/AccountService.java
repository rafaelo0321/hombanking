package com.mindhub.homebanking.service;

import com.mindhub.homebanking.models.Account;

import java.util.List;

public interface AccountService {
    public List<Account> getAccounts();
    Account getAccountId(Long accountId);
    Account findByNumberAccount(String number);

    Account saveAccount(Account account);
}
