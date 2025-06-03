package com.koleksiyoner.commons.results;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DataResult<T> extends Result {
    private T data;

    public DataResult(T data, boolean status, HttpStatus httpStatus, String message) {
        super(status, httpStatus, message);
        this.data = data;
    }

    public DataResult(T data, boolean status, HttpStatus httpStatus) { // mesaj göndermek istemiyorum
        super(status, httpStatus);
        this.data = data;
    }
}