package com.koleksiyoner.commons.results;

import org.springframework.http.HttpStatus;

public class ErrorResult extends Result {

    public ErrorResult(HttpStatus httpStatus) {
        super(false, httpStatus);
    }

    public ErrorResult(HttpStatus httpStatus, String message) {
        super(false, httpStatus, message);
    }
}