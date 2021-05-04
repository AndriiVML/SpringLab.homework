package com.mlav.springboot.travelagency.controller;

import com.mlav.springboot.travelagency.model.entity.Error;
import com.mlav.springboot.travelagency.exception.ServiceException;
import com.mlav.springboot.travelagency.model.ErrorType;
import lombok.extern.slf4j.Slf4j;
import com.mlav.springboot.travelagency.exception.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException: message {}", ex.getMessage());
        return ex.getBindingResult().getAllErrors().stream()
                .map(err -> new Error(err.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE,
                        LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleValidationException(ValidationException ex) {
        log.error("handleValidationException: message {}", ex.getMessages());
        return ex.getMessages().stream()
                .map(message -> new Error(message, ex.getErrorType(), LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handlePropertyReferenceException(PropertyReferenceException ex) {
        log.error("handlePropertyReferenceException: message {}", ex.getMessage());
        return new Error(ex.getMessage(), ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("handleIllegalArgumentException: message {}", ex.getMessage());
        String message = ex.getMessage();
        String searchFor = "Status.";
        if (ex.getMessage().contains("enum")) {
            message = "Unknown status " + ex.getMessage()
                    .substring(message.lastIndexOf(searchFor) + searchFor.length());
        }
        return new Error(message, ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleServiceException(ServiceException ex) {
        log.error("handlerServiceException: " + ex.getMessage(), ex);
        return new Error(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
    }

    //DataIntegrityViolationException is good, SQLIntegrityConstraintViolationException - not good,
    //because Exception will handle it, without proper message
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        //nested SQLIntegrityException
        log.error("handlerDataIntegrityViolationException: " + ex.getCause().getCause(), ex);
        final String causeOfException = ex.getCause().getCause().toString();
        String message;
        if (causeOfException.contains("login")) {
            message = "Login must be unique";
        } else if (causeOfException.contains("email")) {
            message = "Email must be unique";
        } else if (causeOfException.contains("tour_unique")) {
            message = "Tour must be unique";
        } else {
            message = ex.getMessage();
        }
        return new Error(message, ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleException(Exception ex) {
        log.error("handleException: message {}", ex.getMessage());
        return new Error(ex.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
    }
}
