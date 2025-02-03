package com.school.school.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SchoolWebAppExceptionHandler {
    @ExceptionHandler({ExamNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleExamNotFoundException(
            ExamNotFoundException ex){
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler({SchoolClassNotFound.class})
    public ResponseEntity<ErrorMessage> handleSchoolClassNotFound(
            SchoolClassNotFound ex){
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler({SubjectNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleSubjectNotFoundException(
            SubjectNotFoundException ex){
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler({StudentNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleStudentNotFoundException(
            StudentNotFoundException ex){
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus());
    }

    @ExceptionHandler({TeacherNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleTeacherNotFoundException(
            TeacherNotFoundException ex){
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), new HttpHeaders(), ex.getHttpStatus());
    }
}
