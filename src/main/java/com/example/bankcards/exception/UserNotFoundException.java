package com.example.bankcards.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    private final Long userId;

    public UserNotFoundException(Long userId){
        super("User with ID " + userId + " not found");
        this.userId = userId;
    }
}
