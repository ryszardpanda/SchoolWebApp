package com.school.school.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CLASS")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CLASS_NAME", length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "schoolClass")
    private List<Student> students;

    public boolean equals(SchoolClass o) {
        if (this == o) return true;

        if (!(o instanceof SchoolClass))
            return false;

        SchoolClass other = (SchoolClass) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
