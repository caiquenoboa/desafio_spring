package com.mercadolivre.desafioSpring.exceptions;

import com.mercadolivre.desafioSpring.responses.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectExceptionHandler {
    @ExceptionHandler(StandardNotFoundException.class)
    public ResponseEntity<ExceptionResponse> errorUserNotFound(StandardNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(),System.currentTimeMillis()));
    }
}
