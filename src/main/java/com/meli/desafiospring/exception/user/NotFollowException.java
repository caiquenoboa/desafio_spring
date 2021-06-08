package com.meli.desafiospring.exception.user;

import com.meli.desafiospring.exception.DefaultException;

public class NotFollowException extends DefaultException {

    public NotFollowException(String message) {
        super(message);
    }
}
