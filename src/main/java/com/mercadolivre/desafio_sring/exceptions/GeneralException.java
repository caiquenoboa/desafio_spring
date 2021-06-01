package com.mercadolivre.desafio_sring.exceptions;

public class GeneralException extends RuntimeException {
    private String message;
    private Integer status;

    public GeneralException(String message, Integer status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
