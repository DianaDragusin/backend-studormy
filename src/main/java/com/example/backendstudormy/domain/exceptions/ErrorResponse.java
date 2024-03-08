package com.example.backendstudormy.domain.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String errorMessage;
    private Integer businessCode;
}
