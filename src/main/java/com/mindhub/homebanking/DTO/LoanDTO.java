package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoanDTO {
    private Long idLoan;
    private String nameLoan;
    private Double maxAmountLoan;
    private Double loanInterest;
    private List<Integer> paymentsLoans;
    private Set<ClientLoanDTO> clientLoans;

    public LoanDTO(){}

    public LoanDTO(Loan loan) {
        this.idLoan = loan.getId();
        this.nameLoan = loan.getName();
        this.maxAmountLoan = loan.getMaxAmount();
        this.paymentsLoans = loan.getPaymentsLoans();
        this.loanInterest = loan.getLoanInterest();

        this.clientLoans = loan.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
        //Error: No habia indicado el set clientLoanDTO, en la linea 16
    }



    public Long getIdLoan() {
        return idLoan;
    }

    public String getNameLoan() {
        return nameLoan;
    }

    public Double getMaxAmountLoan() {
        return maxAmountLoan;
    }

    public List<Integer> getPaymentsLoans() {
        return paymentsLoans;
    }

    public Set<ClientLoanDTO> getClientLoans() {
        return clientLoans;
    }

    public Double getLoanInterest() {
        return loanInterest;
    }
}
