package com.meli.desafiospring.exception.handler;

import com.meli.desafiospring.exception.DefaultException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ChallengeExceptionHandler {

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ExceptionResponse> challengeExceptionHandler(DefaultException exception){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
