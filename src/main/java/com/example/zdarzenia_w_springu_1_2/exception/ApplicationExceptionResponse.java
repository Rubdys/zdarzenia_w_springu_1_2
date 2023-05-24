package com.example.zdarzenia_w_springu_1_2.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ApplicationExceptionResponse {
    private String errorMessage;
    private LocalDateTime exceptionTime;
}
