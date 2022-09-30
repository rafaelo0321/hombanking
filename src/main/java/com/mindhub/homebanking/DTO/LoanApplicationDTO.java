package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoanApplicationDTO {
    private long id;//Loan
    private Integer payments;//Loans:
    private Double amount; //Client Loans
    private String numberCuentaDestino;//Account:

    public LoanApplicationDTO() {
    }

    //Se arma el constructor con el cuerpo del objeto que voy a pasar en el controlador de los prestamos.


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getPayments() {
        return payments;
    }

    public void setPayments(Integer payments) {
        this.payments = payments;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNumberCuentaDestino() {
        return numberCuentaDestino;
    }

    public void setNumberCuentaDestino(String numberCuentaDestino) {
        this.numberCuentaDestino = numberCuentaDestino;
    }
}
