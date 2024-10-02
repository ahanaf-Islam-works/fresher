package com.explorer.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor
public class ApiException extends RuntimeException{
    private HttpStatus status;
    private String errorMessage;

    public ApiException(String errorMessage) {
        super(errorMessage);
    }
}