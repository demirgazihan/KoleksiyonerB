package com.koleksiyoner.commons.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class Result { // super type

    private boolean status;
    private HttpStatus httpStatus;
    private String message;

    public Result(boolean status, HttpStatus httpStatus) {
        this.status = status;
        this.httpStatus = httpStatus;
    }
}