package com.school.school.controller;

import com.school.school.exceptions.ErrorMessage;
import com.school.school.mapper.SubjectMapper;
import com.school.school.model.ExamDTO;
import com.school.school.model.Subject;
import com.school.school.model.SubjectDTO;
import com.school.school.service.SubjectService;
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
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    @Operation(summary = "Add Subject", tags = "Subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Subject with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @PostMapping("/add")
    public SubjectDTO addSubject(@Valid @RequestBody SubjectDTO subjectDTO){
        return subjectMapper.subjectToSubjectDTO(subjectService.addSubject(subjectDTO));
    }

    @Operation(summary = "Delete Subject", tags = "Subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject deleted",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Subject with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @DeleteMapping("/delete/{id}")
    public void deleteSubjectById(@PathVariable Long id){
        subjectService.deleteSubjectById(id);
    }

    @Operation(summary = "Update Subject", tags = "Subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Subject with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @PutMapping("/update/{id}")
    public SubjectDTO updateSubjectById(@PathVariable Long id, @RequestBody SubjectDTO subjectDTO){
        return subjectMapper.subjectToSubjectDTO(subjectService.updateSubject(id, subjectDTO));
    }

    @Operation(summary = "Get Subjects", tags = "Subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Subject with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @GetMapping("get")
    public Page<SubjectDTO> getSubjects(@ParameterObject Pageable pageable){
        Page<Subject> subject = subjectService.getSubject(pageable);
        return subject.map(subjectMapper::subjectToSubjectDTO);
    }

    @Operation(summary = "Get Subject by id", tags = "Subject")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Subject with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @GetMapping("getById/{id}")
    public SubjectDTO getSubjectById(@PathVariable Long id){
        return subjectMapper.subjectToSubjectDTO(subjectService.getSubjectById(id));
    }
}
