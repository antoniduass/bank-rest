package com.example.bankcards.exception;

import lombok.Getter;

@Getter
public class CardNotFoundException extends RuntimeException{
    private final Long cardId;

    public CardNotFoundException(Long cardId){
        super("Card with ID " + cardId + " not found");
        this.cardId = cardId;
    }
}
