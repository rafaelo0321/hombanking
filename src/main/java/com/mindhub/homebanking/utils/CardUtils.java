package com.mindhub.homebanking.utils;

import com.mindhub.homebanking.models.Client;

//Se usa el operado final para que no heredar clases de la misma
public final class CardUtils {
    // se evita que la clase sea instaciada.
    private CardUtils() {
    }
    public static int getCVV() {
        int maxCvv = 999,aCvv,cv=100;
        aCvv = (int)((Math.random() * (cv + maxCvv)) - cv);
        return aCvv;
    }

    public static String getNumber() {
        int min = 1000, max = 9999;

        String number = (int)((Math.random() * (min + max)) - 1)
                +"-"+ (int)((Math.random() * (min + max)) - 1)
                +"-"+ (int)((Math.random() * (min + max)) - 1)
                +"-"+ (int)((Math.random() * (min + max)) - 1);

        return number;
    }
    public static String getHolder(Client clientAuthentication) {
        return clientAuthentication.getName() + " " + clientAuthentication.getLastName();
    }
}
