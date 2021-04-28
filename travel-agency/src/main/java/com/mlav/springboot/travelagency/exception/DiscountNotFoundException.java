package com.mlav.springboot.travelagency.exception;

import com.mlav.springboot.travelagency.model.ErrorType;

public class DiscountNotFoundException extends ServiceException{
    private static final String DEFAULT_MESSAGE = "Discount is not found!";

    public DiscountNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public DiscountNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
