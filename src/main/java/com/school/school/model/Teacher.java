package com.school.school.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TEACHER")
@ToString(of = {"id", "firstName", "lastName"})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "TEACHER_SUBJECT",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<>();

    public boolean equals(Teacher o) {
        if (this == o) return true;

        if (!(o instanceof Teacher))
            return false;

        Teacher other = (Teacher) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
