package com.school.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExamRatingDTO {
    private ExamRatingKey id;
    private int rating;
}
