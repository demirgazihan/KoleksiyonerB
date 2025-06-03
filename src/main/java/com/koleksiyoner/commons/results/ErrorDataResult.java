package com.koleksiyoner.commons.results;

import org.springframework.http.HttpStatus;

public class ErrorDataResult<T> extends DataResult<T> {

    public ErrorDataResult(T data, HttpStatus httpStatus, String message) {
        super(data, false, httpStatus, message);
    }

    public ErrorDataResult(T data, HttpStatus httpStatus) {
        super(data, false, httpStatus);
    }

    public ErrorDataResult(HttpStatus httpStatus, String message) {
        super(null, false, httpStatus, message);
    }

    public ErrorDataResult(HttpStatus httpStatus) {
        super(null, false, httpStatus);
    }
}