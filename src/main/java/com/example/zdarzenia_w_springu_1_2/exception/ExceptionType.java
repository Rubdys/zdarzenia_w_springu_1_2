package com.example.zdarzenia_w_springu_1_2.exception;

public enum ExceptionType {
    VALIDATION_ERROR("Validation error: {%S}", 400);

    private String message;
    private int httpStatus;

    ExceptionType(String message, int httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
