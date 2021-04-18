package com.mlav.springboot.travelagency.exception;

import com.mlav.springboot.travelagency.model.ErrorType;

public class UserNotUpdateLoginException extends ServiceException{
    private static final String DEFAULT_MESSAGE = "Cannot update login in user!";

    public UserNotUpdateLoginException() {
        super(DEFAULT_MESSAGE);
    }

    public UserNotUpdateLoginException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
