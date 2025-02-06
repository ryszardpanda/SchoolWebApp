package com.school.school.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString(of = {"id", "examName", "dateOfExam"})
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Exam))
            return false;

        Exam other = (Exam) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
