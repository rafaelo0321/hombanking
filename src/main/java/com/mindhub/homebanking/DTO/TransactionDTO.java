package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {
    private Long idTransaction;
    private TransactionType typeTransaction;
    private String detailsTransaction;
    private Double amountTransaction;

    private Double amountPost;
    private LocalDateTime dateTransaction;
    private Boolean showTransaction;

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.idTransaction = transaction.getId();
        this.typeTransaction = transaction.getTypeTransaction();
        this.detailsTransaction = transaction.getDescription();
        this.amountTransaction = transaction.getAmount();
        this.amountPost = transaction.getAmountPost();
        this.dateTransaction = transaction.getCreationDate();
        this.showTransaction = transaction.getShowTransaction();

    }

    public Long getIdTransaction() {
        return idTransaction;
    }
    public TransactionType getTypeTransaction() {
        return typeTransaction;
    }
    public String getDetailsTransaction() {
        return detailsTransaction;
    }

    public Double getAmountTransaction() {
        return amountTransaction;
    }

    public Double getAmountPost() {
        return amountPost;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public Boolean getShowTransaction() {
        return showTransaction;
    }
}
