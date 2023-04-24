package com.example.clinicmanagement.Excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.CONFLICT
)
public class UserRegisteredException extends RuntimeException {
    private final String message;

    public UserRegisteredException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
