package com.two_weeks_backend.two_weeks_backend.DTOs.exceptions;

import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Getter
public class SubExceptionDTO {
    private String field;
    private String message;

    public SubExceptionDTO(ObjectError objectError){
        this.field = ((FieldError) objectError).getField();
        this.message = objectError.getDefaultMessage();
    }
}
