package com.meli.desafiospring.exception.user;

import com.meli.desafiospring.exception.DefaultException;

public class ClientCannotFollowSellerException extends DefaultException {

    public ClientCannotFollowSellerException(String message) {
        super(message);
    }
}
