package com.mindhub.homebanking.models;

public enum CardColor {

    GOLD("Gold"),TITANIUM("Titanium"),SILVER("Silver");

    private CardColor(String color) {
        this.color = color;
    }
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
