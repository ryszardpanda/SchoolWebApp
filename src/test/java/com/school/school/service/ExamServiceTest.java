package com.school.school.service;

import com.school.school.mapper.ExamMapper;
import com.school.school.repository.ExamRatingRepository;
import com.school.school.repository.ExamRepository;
import com.school.school.repository.StudentsRepository;
import com.school.school.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

public class ExamServiceTest {
    private ExamRepository examRepository;
    private ExamMapper examMapper;
    private StudentsRepository studentsRepository;
    private ExamRatingRepository examRatingRepository;
    private SubjectRepository subjectRepository;
    private ExamService examService;

    @BeforeEach
    void setUp() {
        this.examRepository = Mockito.mock(ExamRepository.class);
        this.examMapper = Mappers.getMapper(ExamMapper.class);
        this.studentsRepository = Mockito.mock(StudentsRepository.class);
        this.examRatingRepository = Mockito.mock(ExamRatingRepository.class);
        this.subjectRepository = Mockito.mock(SubjectRepository.class);
        this.examService = new ExamService(examRepository, examMapper, studentsRepository,examRatingRepository,subjectRepository);
    }

//    @Test
//    Exam exam = new Exam(1L, "Matematyka", LocalDateTime.of(2025, 11, 11 , 11, 0,)
}
