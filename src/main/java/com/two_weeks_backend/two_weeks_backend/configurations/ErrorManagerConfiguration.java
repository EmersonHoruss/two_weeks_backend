package com.two_weeks_backend.two_weeks_backend.configurations;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.two_weeks_backend.two_weeks_backend.DTOs.exceptions.ExceptionDTO;
import com.two_weeks_backend.two_weeks_backend.constants.EntityConstraints;
import com.two_weeks_backend.two_weeks_backend.exceptions.NotImplemented;
import com.two_weeks_backend.two_weeks_backend.utils.translator.Translator;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ErrorManagerConfiguration {
@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            DuplicateKeyException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            MissingRequestHeaderException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ResponseEntity<ExceptionDTO> badRequest(Exception ex, HttpServletRequest request) {
        ExceptionDTO exDTO = new ExceptionDTO(ex, request);
        ex.printStackTrace();
        return new ResponseEntity<ExceptionDTO>(exDTO, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ConstraintViolationException.class,
            DataIntegrityViolationException.class
    })
    @ResponseBody
    public ResponseEntity<ExceptionDTO> constraintViolationException(Exception ex, HttpServletRequest request) {
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex.getCause();
        String[] messageErrorsParts = constraintViolationException.getSQLException().getMessage().split("'");
        String[] constraint = messageErrorsParts[3].split("[.]");
        String table = constraint[0];
        String column = constraint[1].split(EntityConstraints.SEPARATOR)[1];
        String columnEs = new Translator().getEs(column);
        String tableEs = new Translator().getEs(table);
        String messageError = "Ya existe un(a) " + tableEs + " con el(la) mismo " + columnEs;
        ExceptionDTO exDTO = new ExceptionDTO(messageError, request);
        return new ResponseEntity<ExceptionDTO>(exDTO, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ResponseEntity<ExceptionDTO> fatalErrorUnexpectedError(Exception ex, HttpServletRequest request) {
        System.out.println("internal server error");
        ex.printStackTrace();
        ExceptionDTO exDTO = new ExceptionDTO(ex, request);
        return new ResponseEntity<ExceptionDTO>(exDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            NotImplemented.class
    })
    @ResponseBody
    public ResponseEntity<ExceptionDTO> notFoundStaticValue(Exception ex, HttpServletRequest request) {
        ExceptionDTO exDTO = new ExceptionDTO(ex, request);
        return new ResponseEntity<ExceptionDTO>(exDTO, HttpStatus.BAD_REQUEST);
    }
}