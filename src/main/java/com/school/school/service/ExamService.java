package com.school.school.service;

import com.school.school.exceptions.ExamNotFoundException;
import com.school.school.mapper.ExamMapper;
import com.school.school.model.Exam;
import com.school.school.model.ExamDTO;
import com.school.school.repository.ExamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    @Transactional
    public Exam addExam(ExamDTO examDTO) {
        Exam exam = examMapper.examDTOtoExam(examDTO);
        return examRepository.save(exam);
    }

    @Transactional
    public void deleteExamById(Long id) {
        Exam examById = examRepository.findById(id).orElseThrow(() -> new ExamNotFoundException("Exam with this id not found",
                HttpStatus.NOT_FOUND));
        examRepository.delete(examById);
    }

    @Transactional
    public Exam updateExam(Long id, ExamDTO examDTO) {
        Exam updatedExam = examMapper.examDTOtoExam(examDTO);
        Exam examById = examRepository.findById(id).orElseThrow(() -> new ExamNotFoundException("Exam with this id not found",
                HttpStatus.NOT_FOUND));
        examById.setExamName(updatedExam.getExamName());
        examById.setDateOfExam(updatedExam.getDateOfExam());
        return examById;
    }

    public Page<Exam> getExams(Pageable pageable) {
        return examRepository.findAll(pageable);
    }

    public Exam getExamById(Long id) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new ExamNotFoundException("Exam with this id is not found",
                HttpStatus.NOT_FOUND));
        return exam;
    }
}
