package com.school.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
}
