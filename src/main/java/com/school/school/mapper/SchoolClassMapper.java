package com.school.school.mapper;

import com.school.school.model.SchoolClass;
import com.school.school.model.SchoolClassDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolClassMapper {
    SchoolClass schoolClassDTOtoSchoolClass(SchoolClassDTO schoolClassDTO);
    SchoolClassDTO schoolClassToSchoolClassDTO(SchoolClass schoolClass);
}

