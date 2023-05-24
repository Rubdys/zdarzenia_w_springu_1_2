package com.example.zdarzenia_w_springu_1_2.controller;

import com.example.zdarzenia_w_springu_1_2.exception.ApplicationException;
import com.example.zdarzenia_w_springu_1_2.exception.ApplicationExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApplicationExceptionResponse> handleGlobal(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApplicationExceptionResponse.builder()
                        .errorMessage("Unknown error")
                        .exceptionTime(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApplicationExceptionResponse> handleApplicationException(ApplicationException err){
        return ResponseEntity.status(err.getHttpStatus())
                .body(ApplicationExceptionResponse.builder()
                        .errorMessage(err.getMessage())
                        .exceptionTime(err.getExceptionTime())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException err){
        Map<String, String> errorsMap = new HashMap<>();
        List<ObjectError> errorsList = err.getBindingResult().getAllErrors();
        errorsList.forEach((errorObject) -> {
            FieldError fieldError = (FieldError) errorObject;
            String name = fieldError.getField();
            String errorMessage = errorObject.getDefaultMessage();
            errorsMap.put(name, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorsMap);
    }
}
