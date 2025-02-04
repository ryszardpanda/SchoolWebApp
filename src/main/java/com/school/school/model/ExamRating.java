package com.school.school.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id"})
@Entity
@Table(name = "EXAM_RATING")
public class ExamRating {
    @EmbeddedId
    private ExamRatingKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private int rating;
}
