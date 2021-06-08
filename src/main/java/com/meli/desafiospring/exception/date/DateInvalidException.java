package com.meli.desafiospring.exception.date;

import com.meli.desafiospring.exception.DefaultException;

public class DateInvalidException extends DefaultException {

    public DateInvalidException(String message) {
        super(message);
    }
}
