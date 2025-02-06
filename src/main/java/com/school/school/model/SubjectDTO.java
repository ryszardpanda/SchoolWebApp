package com.school.school.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SubjectDTO {
    private Long id;
    @NotBlank(message = "Name of the subject cannot be blank")
    private String subjectName;
}
