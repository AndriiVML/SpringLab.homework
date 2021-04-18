package com.mlav.springboot.travelagency.exception;

import com.mlav.springboot.travelagency.model.ErrorType;

public class OrderNotFoundException extends ServiceException{
    private static final String DEFAULT_MESSAGE = "Order is not found!";

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
