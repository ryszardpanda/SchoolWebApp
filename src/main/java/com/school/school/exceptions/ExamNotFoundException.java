package com.school.school.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExamNotFoundException extends RuntimeException{
    private final HttpStatus httpStatus;

    public ExamNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
