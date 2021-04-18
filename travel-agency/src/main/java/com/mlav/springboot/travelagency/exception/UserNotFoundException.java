package com.mlav.springboot.travelagency.exception;

import com.mlav.springboot.travelagency.model.ErrorType;

public class UserNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "User is not found!";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
