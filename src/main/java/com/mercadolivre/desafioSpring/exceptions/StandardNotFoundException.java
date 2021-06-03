package com.mercadolivre.desafioSpring.exceptions;

public class StandardNotFoundException extends RuntimeException{
    public StandardNotFoundException(String message) {
        super(message);
    }
}
