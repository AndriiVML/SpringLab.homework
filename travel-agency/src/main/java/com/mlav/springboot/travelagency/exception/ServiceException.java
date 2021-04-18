package com.mlav.springboot.travelagency.exception;

import com.mlav.springboot.travelagency.model.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ServiceException extends RuntimeException {

    private ErrorType errorType;

    public ServiceException(String message) {
        super(message);
    }

    public ErrorType getErrorType() {
        return ErrorType.FATAL_ERROR_TYPE;
    }

}