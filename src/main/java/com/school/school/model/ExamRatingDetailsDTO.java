package com.school.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRatingDetailsDTO {
    private Long examId;
    private String examName;
    private LocalDateTime dateOfExam;
    private String subjectName;
    private int rating;
}