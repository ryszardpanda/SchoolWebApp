package com.school.school.service;

import com.school.school.exceptions.StudentNotFoundException;
import com.school.school.mapper.StudentMapper;
import com.school.school.model.Student;
import com.school.school.model.StudentDTO;
import com.school.school.repository.StudentsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentsRepository studentsRepository;
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
}
