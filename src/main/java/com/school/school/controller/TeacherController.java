package com.school.school.controller;

import com.school.school.exceptions.ErrorMessage;
import com.school.school.mapper.TeacherMapper;
import com.school.school.model.ExamDTO;
import com.school.school.model.Teacher;
import com.school.school.model.TeacherDTO;
import com.school.school.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @Operation(summary = "Add Teacher", tags = "Teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Teacher with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
    })
    @PostMapping("/add")
    public TeacherDTO addTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherMapper.teacherToTeacherDTO(teacherService.addTeacher(teacherDTO));
    }

    @Operation(summary = "Add Teacher", tags = "Teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher deleted",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Teacher with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
    })
    @DeleteMapping("/delete/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacherById(id);
    }

    @Operation(summary = "Update Teacher", tags = "Teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Teacher with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
    })
    @PutMapping("/update/{id}")
    public TeacherDTO updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        return teacherMapper.teacherToTeacherDTO(teacherService.updateTeacherById(id, teacherDTO));
    }

    @Operation(summary = "Get Teacher", tags = "Teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Teacher with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
    })
    @GetMapping("/get")
    public Page<TeacherDTO> getTeachers(@ParameterObject Pageable pageable) {
        Page<Teacher> teachers = teacherService.getTeachers(pageable);
        return teachers.map(teacherMapper::teacherToTeacherDTO);
    }

    @Operation(summary = "Get Teacher by id", tags = "Teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Teacher with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))}),
    })
    @GetMapping("/getById/{id}")
    public TeacherDTO getTeacherById(@PathVariable Long id) {
        return teacherMapper.teacherToTeacherDTO(teacherService.getTeacherById(id));
    }
}
