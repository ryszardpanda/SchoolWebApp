package com.school.school.controller;

import com.school.school.exceptions.ErrorMessage;
import com.school.school.mapper.StudentMapper;
import com.school.school.model.ExamDTO;
import com.school.school.model.Student;
import com.school.school.model.StudentDTO;
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

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

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
}
