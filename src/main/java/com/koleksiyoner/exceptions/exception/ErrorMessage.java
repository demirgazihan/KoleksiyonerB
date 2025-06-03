package com.koleksiyoner.exceptions.exception;

import com.koleksiyoner.exceptions.enums.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private ExceptionMessage exceptionMessage;

    private String ofStatic;

    public String prepareErrorMessage() {

        StringBuilder builder = new StringBuilder();
        builder.append(exceptionMessage.getMessage());
        if (ofStatic != null) {
            builder.append(" : " + ofStatic);
        }
        return builder.toString();
    }
}
