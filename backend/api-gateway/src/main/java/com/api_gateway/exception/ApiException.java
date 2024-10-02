package com.api_gateway.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class ApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
    }
}