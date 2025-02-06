package com.school.school.mapper;

import com.school.school.model.Student;
import com.school.school.model.StudentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student studentDTOtoStudent(StudentDTO studentDTO);
    StudentDTO studentTostudentDTO(Student student);
}
