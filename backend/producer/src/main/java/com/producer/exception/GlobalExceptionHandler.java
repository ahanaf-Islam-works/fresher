package com.producer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static class GlobalException extends RuntimeException {
        @ExceptionHandler(ApiException.class)
        public ResponseEntity<String> handleApiException(ApiException exception) {
            return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
        }
    }
}
