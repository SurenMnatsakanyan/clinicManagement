package com.example.clinicmanagement.Excpetion;

public class UserRegisteredException extends RuntimeException{
    private final String message;
    public UserRegisteredException(String message) {
       this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
