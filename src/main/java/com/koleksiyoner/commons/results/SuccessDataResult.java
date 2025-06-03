package com.koleksiyoner.commons.results;

import org.springframework.http.HttpStatus;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(T data, HttpStatus httpStatus, String message) {
        super(data, true, httpStatus, message);
    }

    public SuccessDataResult(T data, HttpStatus httpStatus) {
        super(data, true, httpStatus);
    }

    public SuccessDataResult(HttpStatus httpStatus, String message) {
        super(null, true, httpStatus, message);
    }

    public SuccessDataResult(HttpStatus httpStatus) {
        super(null, true, httpStatus);
    }
}