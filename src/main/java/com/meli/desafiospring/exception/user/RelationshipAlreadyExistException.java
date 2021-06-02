package com.meli.desafiospring.exception.user;

public class RelationshipAlreadyExistException extends RuntimeException{

    public RelationshipAlreadyExistException(String message) {
        super(message);
    }
}
