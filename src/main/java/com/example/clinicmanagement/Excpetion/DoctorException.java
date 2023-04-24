package com.example.clinicmanagement.Excpetion;

public class DoctorException extends RuntimeException {
    private final String errorMessage;

    public DoctorException(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
