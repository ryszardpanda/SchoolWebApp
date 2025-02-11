package com.school.school.mapper;

import com.school.school.model.Subject;
import com.school.school.model.SubjectDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject subjectDTOtoSubject(SubjectDTO subjectDTO);
    SubjectDTO subjectToSubjectDTO(Subject subject);
    Set<SubjectDTO> toSubjectDTOs(Set<Subject> subjects);
}
