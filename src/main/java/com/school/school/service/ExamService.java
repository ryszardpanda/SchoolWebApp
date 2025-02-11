package com.school.school.service;

import com.school.school.exceptions.ExamNotFoundException;
import com.school.school.exceptions.StudentNotFoundException;
import com.school.school.exceptions.SubjectNotFoundException;
import com.school.school.mapper.ExamMapper;
import com.school.school.model.*;
import com.school.school.repository.ExamRatingRepository;
import com.school.school.repository.ExamRepository;
import com.school.school.repository.StudentsRepository;
import com.school.school.repository.SubjectRepository;
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
    private final StudentsRepository studentsRepository;
    private final ExamRatingRepository examRatingRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public Exam addExam(ExamDTO examDTO) {
        Exam exam = examMapper.examDTOtoExam(examDTO);
        return examRepository.save(exam);
    }

    @Transactional
    public void deleteExamById(Long id) {
        Exam examById = examRepository.findById(id)
                .orElseThrow(() -> new ExamNotFoundException("Exam with this id not found",
                        HttpStatus.NOT_FOUND));
        examRepository.delete(examById);
    }

    @Transactional
    public Exam updateExam(Long id, ExamDTO examDTO) {
        Exam updatedExam = examMapper.examDTOtoExam(examDTO);
        Exam examById = examRepository.findById(id)
                .orElseThrow(() -> new ExamNotFoundException("Exam with this id not found",
                        HttpStatus.NOT_FOUND));
        examById.setExamName(updatedExam.getExamName());
        examById.setDateOfExam(updatedExam.getDateOfExam());
        return examById;
    }

    public Page<Exam> getExams(Pageable pageable) {
        return examRepository.findAll(pageable);
    }

    public Exam getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ExamNotFoundException("Exam with this id is not found",
                        HttpStatus.NOT_FOUND));
        return exam;
    }

    @Transactional
    public ExamRating assignExamRating(Long studentId, Long examId, int rating) {
        Student student = studentsRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with this id doesnt exist", HttpStatus.NOT_FOUND));
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ExamNotFoundException("Exam with this id is not found", HttpStatus.NOT_FOUND));

        ExamRatingKey key = new ExamRatingKey(studentId, examId);

        ExamRating examRating = examRatingRepository.findById(key)
                .orElseGet(() -> ExamRating.create(student, exam, rating));

        examRating.setRating(rating);

        return examRatingRepository.save(examRating);
    }

    @Transactional
    public Exam assignSubjectToExam(Long examId, Long subjectId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found", HttpStatus.NOT_FOUND));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found", HttpStatus.NOT_FOUND));
        exam.setSubject(subject);
        return examRepository.save(exam);
    }
}
