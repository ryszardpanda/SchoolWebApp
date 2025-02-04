package com.school.school.service;

import com.school.school.exceptions.SchoolClassNotFound;
import com.school.school.exceptions.StudentNotFoundException;
import com.school.school.mapper.StudentMapper;
import com.school.school.model.*;
import com.school.school.repository.SchoolClassRepository;
import com.school.school.repository.StudentsRepository;
import com.school.school.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentsRepository studentsRepository;
    private final SchoolClassRepository classRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public Student addStudent(StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOtoStudent(studentDTO);
        return studentsRepository.save(student);
    }

    @Transactional
    public void deleteStudentById(Long id) {
        Student student = studentsRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with this id doesnt exist",
                HttpStatus.NOT_FOUND));
        studentsRepository.delete(student);
    }

    @Transactional
    public Student updateStudentById(Long id, StudentDTO studentDTO) {
        Student updatedStudent = studentMapper.studentDTOtoStudent(studentDTO);
        Student student = studentsRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with this id doesnt exist",
                HttpStatus.NOT_FOUND));
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        return student;
    }

    public Page<Student> getStudents(Pageable pageable) {
        return studentsRepository.findAll(pageable);
    }

    public Student getStudentById(Long id) {
        Student student = studentsRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with this id doesnt exist",
                HttpStatus.NOT_FOUND));
        return student;
    }

    @Transactional
    public Student assignStudentToClass(Long studentId, Long studentClassId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with this id doesnt exist",
                HttpStatus.NOT_FOUND));
        SchoolClass schoolClass = classRepository.findById(studentClassId).orElseThrow(() -> new SchoolClassNotFound("School class with this id not found",
                HttpStatus.NOT_FOUND));
        student.setSchoolClass(schoolClass);
        return studentsRepository.save(student);
    }

    @Transactional
    public Set<Subject> getAllSubjectsForStudent(Long studentId) {
        Student student = studentsRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found", HttpStatus.NOT_FOUND));

        return student.getRatings()
                .stream()
                .map(ExamRating::getExam)
                .map(Exam::getSubject)
                .collect(Collectors.toSet());
    }

    @Transactional
    public List<ExamRatingDetailsDTO> getAllExamRatingsForStudent(Long studentId) {
        Student student = studentsRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("No student with id ", HttpStatus.NOT_FOUND));

        return student.getRatings()
                .stream()
                .map(er -> {
                    Exam exam = er.getExam();
                    Subject subject = exam.getSubject();

                    return new ExamRatingDetailsDTO(
                            exam.getId(),
                            exam.getExamName(),
                            exam.getDateOfExam(),
                            (subject != null ? subject.getSubjectName() : null),
                            er.getRating()
                    );
                })
                .collect(Collectors.toList());
    }
}
