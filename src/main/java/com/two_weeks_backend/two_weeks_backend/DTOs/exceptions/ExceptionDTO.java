package com.two_weeks_backend.two_weeks_backend.DTOs.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ExceptionDTO {
    private final Date timestamp;
    private String message;
    private final String path;
    private List<SubExceptionDTO> errors;

    public ExceptionDTO(Exception ex, HttpServletRequest request) {
        this.timestamp = new Date();
        this.message = ex.getMessage();
        path = request.getRequestURI();
        initErrors(ex);
    }

    public ExceptionDTO(String message, HttpServletRequest request) {
        this.timestamp = new Date();
        this.message = message;
        this.path = request.getRequestURI();
    }

    private void initErrors(Exception ex) {
        this.errors = new ArrayList<SubExceptionDTO>();
        if (ex instanceof MethodArgumentNotValidException) {
            List<ObjectError> objectErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
            for (ObjectError objectError : objectErrors) {
                this.errors.add(new SubExceptionDTO(objectError));
            }
        } else {
            this.errors = null;
        }
    }
}
