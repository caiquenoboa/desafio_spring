package com.mercadolivre.desafio_sring.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldMessage> messages = new ArrayList<>();

    public ValidationError(Integer status, String message, Long timestemp) {
        super(status, message, timestemp);
    }

    public List<FieldMessage> getMessages() {
        return messages;
    }

    public void addError(String fieldName, String message) {
        this.messages.add(new FieldMessage(fieldName, message));
    }
}
