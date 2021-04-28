package com.mlav.springboot.travelagency.exception;

import com.mlav.springboot.travelagency.model.ErrorType;

import java.util.List;

public class ValidationException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Your data is invalid";

    private ErrorType errorType;

    private List<String> messages;

    public ValidationException() {
        super(DEFAULT_MESSAGE);
    }
    public ValidationException(String message){
        super(message);
    }

    public ValidationException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public ErrorType getErrorType() {
        return ErrorType.FATAL_ERROR_TYPE;
    }
}
