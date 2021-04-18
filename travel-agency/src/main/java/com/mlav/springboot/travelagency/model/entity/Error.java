package com.mlav.springboot.travelagency.model.entity;

import com.mlav.springboot.travelagency.model.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {

    private String message;
    private ErrorType errorType;
    private LocalDateTime timeStamp;

}