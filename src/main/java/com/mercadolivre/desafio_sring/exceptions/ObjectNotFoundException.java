package com.mercadolivre.desafio_sring.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    private String message;

    public ObjectNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
