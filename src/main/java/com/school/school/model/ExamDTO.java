package com.school.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ExamDTO {
    private Long id;
    @NotBlank(message = "Name of the exam cannot be blank")
    private String examName;
    private LocalDateTime dateOfExam;
}
