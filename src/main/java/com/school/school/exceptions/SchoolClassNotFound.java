package com.school.school.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SchoolClassNotFound extends RuntimeException {
    private final HttpStatus httpStatus;

    public SchoolClassNotFound(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
