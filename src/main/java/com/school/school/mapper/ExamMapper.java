package com.school.school.mapper;

import com.school.school.model.Exam;
import com.school.school.model.ExamDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    Exam examDTOtoExam(ExamDTO examDTO);
    ExamDTO examToExamDTO(Exam exam);
}
