package com.mlav.springboot.travelagency.exception;

import com.mlav.springboot.travelagency.model.ErrorType;

import java.util.List;

public class DiscountPatchException extends ValidationException {

    private static final String DEFAULT_MESSAGE = "Discount data is invalid";

    public DiscountPatchException() {
        super(DEFAULT_MESSAGE);
    }

    public DiscountPatchException(List<String> messages){
        super(messages);
    }


    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }

}
