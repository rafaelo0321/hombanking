package com.mindhub.homebanking.utils;

import java.util.Random;

public final class AccountUtils {
    private AccountUtils() {
    }
    public static String getNumberAccount() {
        Random aleator = new Random();

        int min = 1, max = 99999999,numeroVariado;

        numeroVariado = aleator.nextInt((min + max) - 1);

        String number = String.format("VIN%8d",numeroVariado);

        return number;
    }
}
