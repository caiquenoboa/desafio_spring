package com.meli.desafiospring.exception.user;

import com.meli.desafiospring.exception.DefaultException;

public class ClientHasNotFollowerException extends DefaultException {

    public ClientHasNotFollowerException(String message) {
        super(message);
    }
}
