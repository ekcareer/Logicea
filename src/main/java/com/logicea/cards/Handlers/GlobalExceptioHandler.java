package com.logicea.cards.Handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class GlobalExceptioHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleException(IllegalStateException exception) {

        return ResponseEntity.badRequest().body(exception.getMessage());

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleException(EntityNotFoundException exception) {

        return ResponseEntity.badRequest().body(exception.getMessage());

    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleException(ValidationException exception) {

        return ResponseEntity.badRequest().body(exception.getMessage());

    }

}
