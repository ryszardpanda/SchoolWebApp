package com.school.school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
@EqualsAndHashCode
public class ExamRatingKey implements Serializable {

    @Column(name = "student_id")
    Long studentId;

    @Column(name = "exam_id")
    Long examId;
}
