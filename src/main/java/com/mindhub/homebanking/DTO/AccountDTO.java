package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Card;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private Long idAccount;
    private String numberAccount;
    private LocalDateTime creationDateAccount;
    private Double balanceAccount;
    private AccountType accountType;
    private Boolean switchAccount;
    private Set<TransactionDTO> transactions;
    private Card cards;

    public AccountDTO() {
    }

    public AccountDTO(Account account) {
        this.idAccount = account.getId();
        this.numberAccount = account.getNumber();
        this.creationDateAccount = account.getCreationDate();
        this.balanceAccount = account.getBalance();
        this.accountType = account.getAccountType();
        this.switchAccount = account.getSwitchAccount();

        this.cards = account.getCard_id();
        this.transactions = account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public LocalDateTime getCreationDateAccount() {
        return creationDateAccount;
    }

    public Double getBalanceAccount() {
        return balanceAccount;
    }

    public Boolean getSwitchAccount() {
        return switchAccount;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }

    public Card  getCard() {
        return cards;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
