package com.meli.desafiospring.exception.user.handler;

import com.meli.desafiospring.exception.handler.ExceptionResponse;
import com.meli.desafiospring.exception.user.ClientCannotFollowSellerException;
import com.meli.desafiospring.exception.user.RelationshipAlreadyExistException;
import com.meli.desafiospring.exception.user.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, ClientCannotFollowSellerException.class,
            RelationshipAlreadyExistException.class})
    public ResponseEntity<ExceptionResponse> userExceptionHandler(RuntimeException exception){
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
