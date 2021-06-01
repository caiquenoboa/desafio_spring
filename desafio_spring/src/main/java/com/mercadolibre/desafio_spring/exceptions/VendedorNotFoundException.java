package com.mercadolibre.desafio_spring.exceptions;

public class VendedorNotFoundException extends RuntimeException {

    public VendedorNotFoundException(String message) {
        super(message);
    }

    public VendedorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}