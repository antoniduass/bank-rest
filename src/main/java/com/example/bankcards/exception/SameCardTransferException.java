package com.example.bankcards.exception;

public class SameCardTransferException extends RuntimeException{
    public SameCardTransferException(){
        super("Cannot transfer to the same card");
    }

    public SameCardTransferException(String message){
        super(message);
    }
}
