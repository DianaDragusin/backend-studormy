package com.example.backendstudormy.domain.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(exception.getMessage());
        response.setBusinessCode(exception.getBusinessCode());

        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleThrowable(Throwable throwable) {
        ExceptionType type = ExceptionType.SERVER_ERROR;

        ErrorResponse response = new ErrorResponse();
        response.setErrorMessage(type.getErrorMessage());
        response.setBusinessCode(type.getBusinessCode());
        throwable.getStackTrace();

        return ResponseEntity.status(type.getHttpStatus()).body(response);
    }
}
