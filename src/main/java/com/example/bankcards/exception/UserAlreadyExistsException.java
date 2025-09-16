package com.example.bankcards.exception;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException{
    private final String username;

    public UserAlreadyExistsException(String username){
        super("User with username '" + username + "' already exists");
        this.username = username;
    }
}
