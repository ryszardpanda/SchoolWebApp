package com.school.school.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class SchoolWebAppExceptionHandler {
    @ExceptionHandler({ExamNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleExamNotFoundException(
            ExamNotFoundException ex) {
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler({SchoolClassNotFound.class})
    public ResponseEntity<ErrorMessage> handleSchoolClassNotFound(
            SchoolClassNotFound ex) {
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler({SubjectNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleSubjectNotFoundException(
            SubjectNotFoundException ex) {
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler({StudentNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleStudentNotFoundException(
            StudentNotFoundException ex) {
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler({TeacherNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleTeacherNotFoundException(
            TeacherNotFoundException ex) {
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorMessage> handleDefaultException(
            RuntimeException ex) {
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage("Unknown Error"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
