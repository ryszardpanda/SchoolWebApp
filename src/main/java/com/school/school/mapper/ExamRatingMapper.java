package com.school.school.mapper;

import com.school.school.model.ExamRating;
import com.school.school.model.ExamRatingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamRatingMapper {
    ExamRatingDTO examRatingToExamRatingDTO(ExamRating examRating);
}
