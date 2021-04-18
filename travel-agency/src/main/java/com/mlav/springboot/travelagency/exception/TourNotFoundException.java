package com.mlav.springboot.travelagency.exception;

import com.mlav.springboot.travelagency.model.ErrorType;

public class TourNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Tour is not found!";

    public TourNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TourNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
