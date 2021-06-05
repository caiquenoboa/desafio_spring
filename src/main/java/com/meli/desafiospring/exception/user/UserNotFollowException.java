package com.meli.desafiospring.exception.user;

public class UserNotFollowException extends RuntimeException{

    public UserNotFollowException(String message) {
        super(message);
    }
}
