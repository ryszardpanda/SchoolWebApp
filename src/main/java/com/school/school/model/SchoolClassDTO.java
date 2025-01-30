package com.school.school.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SchoolClassDTO {
    private Long id;
    @NotBlank(message = "Name of the exam cannot be blank")
    private String name;
}
