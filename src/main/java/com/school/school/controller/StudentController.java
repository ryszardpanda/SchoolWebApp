package com.school.school.controller;

import com.school.school.exceptions.ErrorMessage;
import com.school.school.mapper.StudentMapper;
import com.school.school.mapper.SubjectMapper;
import com.school.school.model.*;
import com.school.school.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final SubjectMapper subjectMapper;

    @Operation(summary = "Add Student", tags = "Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Student with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
    })
    @PostMapping("/add")
    public StudentDTO addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentMapper.studentTostudentDTO(studentService.addStudent(studentDTO));
    }

    @Operation(summary = "Delete Student", tags = "Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student deleted",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Student with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
    })
    @DeleteMapping("/delete/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

    @Operation(summary = "Update Student", tags = "Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Student with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
    })
    @PutMapping("/update/{id}")
    public StudentDTO updateStudentById(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentMapper.studentTostudentDTO(studentService.updateStudentById(id, studentDTO));
    }

    @Operation(summary = "Get Student", tags = "Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Student with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
    })
    @GetMapping("/get")
    public Page<StudentDTO> getStudents(@ParameterObject Pageable pageable) {
        Page<Student> students = studentService.getStudents(pageable);
        return students.map(studentMapper::studentTostudentDTO);
    }

    @Operation(summary = "Get Student by id", tags = "Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Student with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
    })
    @GetMapping("/getById/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentMapper.studentTostudentDTO(studentService.getStudentById(id));
    }

    @Operation(summary = "Assign student to class", tags = "Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student assigned to class",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Student with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "404", description = "Class with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content})
    })
    @PatchMapping("/{studentId}/assign-class/{classId}")
    public StudentDTO assignStudentToClass(@PathVariable Long studentId, @PathVariable Long classId){
        return studentMapper.studentTostudentDTO(studentService.assignStudentToClass(studentId,classId));
    }

    @Operation(summary = "Get subjects of student", tags = "Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subjects of studentd returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Student with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content})
    })
    @GetMapping("/{studentId}/subjects")
    public Set<SubjectDTO> getSubjectsForStudent(@PathVariable Long studentId) {
        Set<Subject> subjects = studentService.getAllSubjectsForStudent(studentId);
        return subjectMapper.toSubjectDTOs(subjects);
    }

    @GetMapping("/{studentId}/exam-ratings")
    public List<ExamRatingDetailsDTO> getExamRatingsForStudent(@PathVariable Long studentId) {
        return studentService.getAllExamRatingsForStudent(studentId);
    }
}
