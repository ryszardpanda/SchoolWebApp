package com.school.school.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(of = {"id", "firstName", "lastName"})
@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "student")
    private Set<ExamRating> ratings;
}
