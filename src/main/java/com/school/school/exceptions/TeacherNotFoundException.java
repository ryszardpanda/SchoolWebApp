package com.school.school.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TeacherNotFoundException extends RuntimeException{
    private HttpStatus httpStatus;
    public TeacherNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
