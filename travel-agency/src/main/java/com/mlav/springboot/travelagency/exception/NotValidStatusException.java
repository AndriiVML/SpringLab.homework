package com.mlav.springboot.travelagency.exception;

import com.mlav.springboot.travelagency.model.ErrorType;

public class NotValidStatusException extends ServiceException{
    private static final String DEFAULT_MESSAGE = "Status is not valid!";

    public NotValidStatusException() {
        super(DEFAULT_MESSAGE);
    }

    public NotValidStatusException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
