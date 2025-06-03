package com.koleksiyoner.commons.results;

import org.springframework.http.HttpStatus;

public class SuccessResult extends Result {

    public SuccessResult(HttpStatus httpStatus) {
        super(true, httpStatus);
    }

    public SuccessResult(HttpStatus httpStatus, String message) {
        super(true, httpStatus, message);
    }
}