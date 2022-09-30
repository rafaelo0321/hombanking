package com.mindhub.homebanking.DTO;


import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;

public class ClientLoanDTO {
    private Long idClientLoan;
    private Double AmountClientLoan;
    private Integer PaymentClientLoan;

    private Client clientLoan;
    private Loan loanClient;
    public ClientLoanDTO() {

    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.idClientLoan = clientLoan.getId();
        this.AmountClientLoan = clientLoan.getAmount();
        this.PaymentClientLoan = clientLoan.getPayment();
        this.clientLoan = clientLoan.getClientLoan();
        this.loanClient = clientLoan.getLoanClient();
    }

    public Long getIdClientLoan() {
        return idClientLoan;
    }

    public Double getAmountClientLoan() {
        return AmountClientLoan;
    }

    public Integer getPaymentClientLoan() {
        return PaymentClientLoan;
    }

    public Client getClientLoan() {
        return clientLoan;
    }

    public Loan getLoanClient() {
        return loanClient;
    }
}
