package com.school.school.mapper;

import com.school.school.model.Teacher;
import com.school.school.model.TeacherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher teacherDTOtoTeacher(TeacherDTO teacherDTO);
    TeacherDTO teacherToTeacherDTO(Teacher teacher);
}
