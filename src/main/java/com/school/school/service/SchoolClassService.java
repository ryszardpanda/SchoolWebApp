package com.school.school.service;

import com.school.school.exceptions.SchoolClassNotFound;
import com.school.school.mapper.SchoolClassMapper;
import com.school.school.model.SchoolClass;
import com.school.school.model.SchoolClassDTO;
import com.school.school.repository.SchoolClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassService {
    private SchoolClassRepository schoolClassRepository;
    private SchoolClassMapper schoolClassMapper;

    @Transactional
    public SchoolClass addSchoolClass(SchoolClassDTO schoolClassDTO) {
        SchoolClass schoolClass = schoolClassMapper.schoolClassDTOtoSchoolClass(schoolClassDTO);
        return schoolClassRepository.save(schoolClass);
    }

    @Transactional
    public void deleteSchoolById(Long id) {
        SchoolClass schoolClass = schoolClassRepository.findById(id).orElseThrow(() -> new SchoolClassNotFound("School class with this id not found",
                HttpStatus.NOT_FOUND));
        schoolClassRepository.delete(schoolClass);
    }

    @Transactional
    public SchoolClass updateSchoolById(Long id, SchoolClassDTO schoolClassDTO){
        SchoolClass schoolClass = schoolClassMapper.schoolClassDTOtoSchoolClass(schoolClassDTO);
        SchoolClass schoolClassById = schoolClassRepository.findById(id).orElseThrow(() -> new SchoolClassNotFound("School class with this id not found",
                HttpStatus.NOT_FOUND));
        schoolClassById.setName(schoolClassDTO.getName());
        return schoolClassById;
    }

    public Page<SchoolClass> getSchoolClasses(Pageable pageable){
       return schoolClassRepository.findAll(pageable);
    }

    public SchoolClass getSchoolClassById(Long id){
        SchoolClass schoolClass = schoolClassRepository.findById(id).orElseThrow(() -> new SchoolClassNotFound("School class with this id not found",
                HttpStatus.NOT_FOUND));
        return schoolClass;
    }
}
