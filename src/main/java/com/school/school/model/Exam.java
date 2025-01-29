package com.school.school.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "EXAM")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "EXAM_NAME", length = 50, nullable = false)
    private String examName;
    @Column(name = "DATE_OF_EXAM", length = 50, nullable = false)
    private LocalDateTime dateOfExam;

    @ManyToOne
    private Subject subject;

    @OneToMany(mappedBy = "exam")
    private Set<ExamRating> ratings;
}
