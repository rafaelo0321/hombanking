package com.mindhub.homebanking.models;

public enum CardType {
     CREDITO("Credit"),DEBITO("Debit");

    private CardType(String txType) {
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
