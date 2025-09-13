package com.example.bankcards.util;

import java.util.Random;

public final class CardNumberGeneratorUtil {
    private static final String BIN = "2200";
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final Random random = new Random();

    private CardNumberGeneratorUtil(){

    }

    public static String generateCardNumber(){
        StringBuilder cardNumber = new StringBuilder(BIN);

        while (cardNumber.length() < CARD_NUMBER_LENGTH){
            cardNumber.append(random.nextInt(10));
        }

        return cardNumber.toString();
    }
}
