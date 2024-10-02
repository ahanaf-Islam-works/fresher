package com.api_gateway.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleGlobalException(ApiException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, ex.getStatus());
    }
}