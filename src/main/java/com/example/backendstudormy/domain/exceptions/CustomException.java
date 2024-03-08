package com.example.backendstudormy.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class CustomException extends RuntimeException {
    private final Integer businessCode;
    private final HttpStatus httpStatus;

    public CustomException(ExceptionType exceptionType) {
        super(exceptionType.getErrorMessage());
        httpStatus = exceptionType.getHttpStatus();
        businessCode = exceptionType.getBusinessCode();
    }

    public CustomException(ExceptionType exceptionType, String argument) {
        super(String.format(exceptionType.getErrorMessage(), argument));
        httpStatus = exceptionType.getHttpStatus();
        businessCode = exceptionType.getBusinessCode();
    }

    public CustomException(ExceptionType exceptionType, List<String> arguments) {
        super(String.format(exceptionType.getErrorMessage(), arguments.toArray()));
        httpStatus = exceptionType.getHttpStatus();
        businessCode = exceptionType.getBusinessCode();
    }
}
