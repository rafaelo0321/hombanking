package com.mindhub.homebanking.models;
//Enumera los tipo de datos que se van a usar. estas son de tipo Constante

public enum TransactionType {
//Defino las variables que contendran los enun, estan en mayuscula por ser constante
    CREDITO("Credit"),DEBITO("Debit");
    // se evita que la clase sea instaciada.
    private TransactionType(String txType) {
        this.txType = txType;
    }
    private String txType;

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }
}
