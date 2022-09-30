package com.mindhub.homebanking.DTO;

public class PagosDTO {
    //contener número, código de seguridad, monto y descripción de la operación.
    private String number;
    private String cvv;
    private Double amount;
    private String description;

    public PagosDTO() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
