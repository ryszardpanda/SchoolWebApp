package com.school.school.service;

import com.school.school.exceptions.SubjectNotFoundException;
import com.school.school.mapper.SubjectMapper;
import com.school.school.model.Subject;
import com.school.school.model.SubjectDTO;
import com.school.school.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;


    @Transactional
    public Subject addSubject(SubjectDTO subjectDTO) {
        Subject subject = subjectMapper.subjectDTOtoSubject(subjectDTO);
        return subjectRepository.save(subject);
    }

    @Transactional
    public void deleteSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException("Subject with this id not found",
                HttpStatus.NOT_FOUND));
        subjectRepository.delete(subject);
    }

    @Transactional
    public Subject updateSubject(Long id, SubjectDTO subjectDTO) {
        Subject updatedSubject = subjectMapper.subjectDTOtoSubject(subjectDTO);
        Subject subjectById = subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException("Subject with this id not found",
                HttpStatus.NOT_FOUND));
        subjectById.setSubjectName(updatedSubject.getSubjectName());
        return subjectById;
    }

    public Page<Subject> getSubject(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    public Subject getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException("Subject with this id is not found",
                HttpStatus.NOT_FOUND));
        return subject;
    }
}
