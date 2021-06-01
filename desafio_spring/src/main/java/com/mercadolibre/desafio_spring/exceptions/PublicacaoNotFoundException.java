package com.mercadolibre.desafio_spring.exceptions;


public class PublicacaoNotFoundException extends RuntimeException {

    public PublicacaoNotFoundException(String message) {
        super(message);
    }

    public PublicacaoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
