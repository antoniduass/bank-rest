package com.example.bankcards.util;

public final class CardNumberMaskerUtil{
    private CardNumberMaskerUtil(){

    }

    public static String maskCardNumber(String cardNumber){
        if (cardNumber == null){
            throw new IllegalArgumentException("Card number cannot be null");
        }

        return "**** **** **** " + cardNumber.substring(12);
    }
}
