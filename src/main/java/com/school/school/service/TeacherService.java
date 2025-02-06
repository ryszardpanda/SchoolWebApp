package com.school.school.service;

import com.school.school.exceptions.SubjectNotFoundException;
import com.school.school.exceptions.TeacherNotFoundException;
import com.school.school.mapper.TeacherMapper;
import com.school.school.model.Subject;
import com.school.school.model.Teacher;
import com.school.school.model.TeacherDTO;
import com.school.school.repository.SubjectRepository;
import com.school.school.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherMapper teacherMapper;

    @Transactional
    public Teacher addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.teacherDTOtoTeacher(teacherDTO);
        return teacherRepository.save(teacher);
    }

    @Transactional
    public void deleteTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with this ID doesnt exist",
                HttpStatus.NOT_FOUND));
        teacherRepository.delete(teacher);
    }

    @Transactional
    public Teacher updateTeacherById(Long id, TeacherDTO teacherDTO) {
        Teacher updatedTeacher = teacherMapper.teacherDTOtoTeacher(teacherDTO);
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with this ID doesnt exist",
                HttpStatus.NOT_FOUND));
        teacher.setFirstName(updatedTeacher.getFirstName());
        teacher.setLastName(updatedTeacher.getLastName());
        return teacher;
    }

    public Page<Teacher> getTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException("Teacher with this ID doesnt exist",
                HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Teacher assignTeacherToSubject(Long teacherId, Long subjectId){
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with this ID doesnt exist",
                HttpStatus.NOT_FOUND));
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject with this id not found",
                HttpStatus.NOT_FOUND));
       teacher.getSubjects().add(subject);
       return teacher;
    }
}
