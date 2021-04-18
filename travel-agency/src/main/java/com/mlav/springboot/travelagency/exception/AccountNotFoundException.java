package com.mlav.springboot.travelagency.exception;

import com.mlav.springboot.travelagency.model.ErrorType;

public class AccountNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Account is not found!";

    public AccountNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public AccountNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
