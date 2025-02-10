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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
        this.examService = new ExamService(examRepository, examMapper, studentsRepository, examRatingRepository, subjectRepository);
    }

    @Test
    public void addExam_ExamAdded_ExamReturned() {
        //given
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());

        ExamDTO examDTO = new ExamDTO(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0));
        Mockito.when(examRepository.save(Mockito.any())).thenReturn(exam);
        //when
        Exam result = examService.addExam(examDTO);
        //then
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Matematyka", result.getExamName());
        Assertions.assertEquals(LocalDateTime.of(2025, 11, 11, 11, 0, 0), result.getDateOfExam());
    }

    @Test
    public void deleteExam_ExamFound_ExamDeleted() {
        //given
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());
        Mockito.when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));
        //when
        examService.deleteExamById(exam.getId());
        //then
        Mockito.verify(examRepository).delete(exam);
    }

    @Test
    public void deleteExam_ExamNotFound_ExamNotDeleted() {
        //given
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());
        Mockito.when(examRepository.findById(exam.getId())).thenReturn(Optional.empty());
        //when
        ExamNotFoundException result = Assertions.assertThrows(ExamNotFoundException.class, () -> examService.deleteExamById(exam.getId()));
        //then
        Assertions.assertEquals("Exam with this id not found", result.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getHttpStatus());
    }

    @Test
    public void updateExam_ExamFound_ExamUpdated() {
        //given
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());

        ExamDTO examDTOtoUpdate = new ExamDTO(1L, "Matematykaaaaaaaaaaa",
                LocalDateTime.of(2024, 11, 11, 11, 0, 0));

        Mockito.when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        //when
        Exam result = examService.updateExam(1L, examDTOtoUpdate);
        //then
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Matematykaaaaaaaaaaa", result.getExamName());
        Assertions.assertEquals(LocalDateTime.of(2024, 11, 11, 11, 0, 0), result.getDateOfExam());
    }

    @Test
    public void updateExam_ExamNotFound_ExamNotUpdated() {
        //given
        ExamDTO examDTOtoUpdate = new ExamDTO(1L, "Matematykaaaaaaaaaaa",
                LocalDateTime.of(2024, 11, 11, 11, 0, 0));
        Mockito.when(examRepository.findById(1L)).thenReturn(Optional.empty());
        //when

        ExamNotFoundException result = Assertions.assertThrows(ExamNotFoundException.class, () -> examService.updateExam(1L, examDTOtoUpdate));
        //then
        Assertions.assertEquals("Exam with this id not found", result.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getHttpStatus());
    }

    @Test
    public void getExams_ExamsReturned() {
        //given
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());
        Exam exam1 = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());

        List<Exam> listOfExams = List.of(exam, exam1);
        PageImpl<Exam> pageOfExams = new PageImpl<>(listOfExams);

        PageRequest pageRequest = PageRequest.of(0, 2);

        Mockito.when(examRepository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(pageOfExams);
        //when
        Page<Exam> result = examService.getExams(pageRequest);
        Assertions.assertEquals(2, result.getTotalElements());
        Assertions.assertEquals(listOfExams, result.getContent());
    }

    @Test
    public void getExamById_ExamExist_ExamReturned() {
        //given
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());
        Mockito.when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        //when
        Exam result = examService.getExamById(1L);
        //then
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Matematyka", result.getExamName());
        Assertions.assertEquals(LocalDateTime.of(2025, 11, 11, 11, 0, 0), result.getDateOfExam());
    }

    @Test
    public void getExamById_ExamNotExist_ExamNotReturned() {
        //given
        Mockito.when(examRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        ExamNotFoundException result = Assertions.assertThrows(ExamNotFoundException.class, () -> examService.getExamById(1L));
        //then
        Assertions.assertEquals("Exam with this id is not found", result.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getHttpStatus());
    }

    @Test
    public void assignExamRating_StudentAndExamExist_RatingAssigned() {
        //given
        Student student = new Student(1L, "Adam", "Adamski", new SchoolClass(), new HashSet<>());
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());

        ExamRatingKey examRatingKey = new ExamRatingKey(student.getId(), exam.getId());
        ExamRating examRating = ExamRating.create(student, exam, 5);

        Mockito.when(studentsRepository.findById(1L))
                .thenReturn(Optional.of(student));
        Mockito.when(examRepository.findById(1L))
                .thenReturn(Optional.of(exam));
        Mockito.when(examRatingRepository.findById(examRatingKey))
                .thenReturn(Optional.of(examRating));
        Mockito.when(examRatingRepository.save(Mockito.any(ExamRating.class)))
                .thenReturn(examRating);
        //when
        ExamRating result = examService.assignExamRating(student.getId(), exam.getId(), 5);
        //then
        Assertions.assertEquals(examRatingKey, result.getId());
        Assertions.assertEquals(student.getFirstName(), result.getStudent().getFirstName());
        Assertions.assertEquals(student.getLastName(), result.getStudent().getLastName());
        Assertions.assertEquals(5, result.getRating());
        Assertions.assertEquals(exam.getExamName(), result.getExam().getExamName());
    }

    @Test
    public void assignExamRating_StudentDoesNotExist_RatingNotAssigned() {
        //given
        Student student = new Student(1L, "Adam", "Adamski", new SchoolClass(), new HashSet<>());
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());
        Mockito.when(studentsRepository.findById(student.getId()))
                .thenReturn(Optional.empty());
        //when
        StudentNotFoundException result = Assertions.assertThrows(StudentNotFoundException.class,
                () -> examService.assignExamRating(student.getId(), exam.getId(), 5));
        //then
        Assertions.assertEquals("Student with this id doesnt exist", result.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getHttpStatus());
    }
    @Test
    public void assignExamRating_ExamDoesNotExist_RatingNotAssigned() {
        //given
        Student student = new Student(1L, "Adam", "Adamski", new SchoolClass(), new HashSet<>());
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());

        Mockito.when(studentsRepository.findById(student.getId())).thenReturn(Optional.of(student));
        Mockito.when(examRepository.findById(exam.getId())).thenReturn(Optional.empty());
        //when
        ExamNotFoundException result = Assertions.assertThrows(ExamNotFoundException.class, () -> examService.assignExamRating(student.getId(), exam.getId(), 5));
        //then
        Assertions.assertEquals("Exam with this id is not found", result.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getHttpStatus());
    }

    @Test
    public void assignSubjectToExam_subjectAndExamExist_SubjectAssignedToExam(){
        //given
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());
        Subject subject = new Subject(1L, "Matematyka", new HashSet<>());

        Mockito.when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));
        Mockito.when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));
        Mockito.when(examRepository.save(exam)).thenReturn(exam);
        //when
        Exam result = examService.assignSubjectToExam(exam.getId(), subject.getId());
        //then
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals(1L, result.getSubject().getId());
        Assertions.assertEquals("Matematyka", result.getExamName());
    }

    @Test
    public void assignSubjectToExam_ExamNotExist_SubjectNotAssignedToExam(){
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());
        Subject subject = new Subject(1L, "Matematyka", new HashSet<>());
        Mockito.when(examRepository.findById(exam.getId())).thenReturn(Optional.empty());
        //when
        ExamNotFoundException result = Assertions.assertThrows(ExamNotFoundException.class, () -> examService.assignSubjectToExam(subject.getId(), exam.getId()));
        //then
        Assertions.assertEquals("Exam not found", result.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getHttpStatus());
    }

    @Test
    public void assignSubjectToExam_SubjectNotExist_SubjectNotAssignedToExam(){
        Exam exam = new Exam(1L, "Matematyka",
                LocalDateTime.of(2025, 11, 11, 11, 0, 0),
                new Subject(), new HashSet<>());
        Subject subject = new Subject(1L, "Matematyka", new HashSet<>());
        Mockito.when(examRepository.findById(exam.getId())).thenReturn(Optional.of(exam));
        Mockito.when(subjectRepository.findById(subject.getId())).thenReturn(Optional.empty());
        //when
        SubjectNotFoundException result = Assertions.assertThrows(SubjectNotFoundException.class, () -> examService.assignSubjectToExam(exam.getId(), subject.getId()));
        //then
        Assertions.assertEquals("Subject not found", result.getMessage());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getHttpStatus());
    }
}
