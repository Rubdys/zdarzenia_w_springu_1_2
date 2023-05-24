package com.example.zdarzenia_w_springu_1_2.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApplicationException extends RuntimeException {
    private final LocalDateTime exceptionTime;
    private final int httpStatus;

    public ApplicationException(ExceptionType exceptionType, Object... messageParameters) {
        super(String.format(exceptionType.getMessage(), messageParameters));
        this.exceptionTime = LocalDateTime.now();
        this.httpStatus = exceptionType.getHttpStatus();
    }
}
