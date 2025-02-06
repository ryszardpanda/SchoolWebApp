package com.school.school.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    public static ExamRating create(Student student, Exam exam, int rating) {
        ExamRating examRating = new ExamRating();

        ExamRatingKey key = new ExamRatingKey(student.getId(), exam.getId());

        examRating.setId(key);
        examRating.setStudent(student);
        examRating.setExam(exam);
        examRating.setRating(rating);

        return examRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ExamRating))
            return false;

        ExamRating other = (ExamRating) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
