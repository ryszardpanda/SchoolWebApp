package com.school.school.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "EXAM_RATING")
public class ExamRating {
    @EmbeddedId
    ExamRatingKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Students student;

    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    Exam exam;

    int rating;
}
