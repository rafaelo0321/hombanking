package com.mindhub.homebanking.DTO;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;

public class CreateLoanDTO {
    private long id;

    private String name;
    private Double maxAmount;
    @ElementCollection
    @Column(name = "paymentsLoan")
    private List<Integer> paymentsLoans;

    private Double loanInterest;

    public CreateLoanDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPaymentsLoans() {
        return paymentsLoans;
    }

    public void setPaymentsLoans(List<Integer> paymentsLoans) {
        this.paymentsLoans = paymentsLoans;
    }

    public Double getLoanInterest() {
        return loanInterest;
    }

    public void setLoanInterest(Double loanInterest) {
        this.loanInterest = loanInterest;
    }
}
