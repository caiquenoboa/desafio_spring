package com.mercadolivre.desafio_sring.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> errorObjectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<StandardError> errorObjectNotFound(GeneralException e, HttpServletRequest request) {
        StandardError standardError = new StandardError(e.getStatus(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(e.getStatus()).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> errorValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation failed", System.currentTimeMillis());

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<StandardError> errorParseDate(DateTimeParseException e, HttpServletRequest request) {
        String message = "The String Date '" + e.getParsedString() + "' is invalid";

        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), message, System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> errorJpaUnique(DataIntegrityViolationException e, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), "This record already exists", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
}
