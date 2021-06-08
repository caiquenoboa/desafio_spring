package com.meli.desafiospring.exception.user;

import com.meli.desafiospring.exception.DefaultException;

public class RelationshipAlreadyExistException extends DefaultException {

    public RelationshipAlreadyExistException(String message) {
        super(message);
    }
}
