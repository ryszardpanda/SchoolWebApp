package com.school.school.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SubjectNotFoundException extends RuntimeException {
  private HttpStatus httpStatus;
    public SubjectNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
