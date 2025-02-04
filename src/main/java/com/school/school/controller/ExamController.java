package com.school.school.controller;

import com.school.school.exceptions.ErrorMessage;
import com.school.school.mapper.ExamMapper;
import com.school.school.mapper.ExamRatingMapper;
import com.school.school.model.Exam;
import com.school.school.model.ExamDTO;
import com.school.school.model.ExamRatingDTO;
import com.school.school.service.ExamService;
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
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;
    private final ExamMapper examMapper;
    private final ExamRatingMapper examRatingMapper;

    @Operation(summary = "Add Exam", tags = "Exam")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Exam with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @PostMapping("/add")
    public ExamDTO addExam(@Valid @RequestBody ExamDTO examDTO){
       return examMapper.examToExamDTO(examService.addExam(examDTO));
    }

    @Operation(summary = "Delete Exam", tags = "Exam")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam deleted",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Exam with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @DeleteMapping("/delete/{id}")
    public void deleteExamById(@PathVariable Long id){
        examService.deleteExamById(id);
    }

    @Operation(summary = "Update Exam", tags = "Exam")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Exam with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @PutMapping("/update/{id}")
    public ExamDTO updateExamById(@PathVariable Long id, @RequestBody ExamDTO examDTO){
        return examMapper.examToExamDTO(examService.updateExam(id, examDTO));
    }

    @Operation(summary = "Get Exams", tags = "Exam")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exams returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Exam with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @GetMapping("/get")
    public Page<ExamDTO> getExams(@ParameterObject Pageable pageable){
        Page<Exam> exams = examService.getExams(pageable);
        return exams.map(examMapper::examToExamDTO);
    }

    @Operation(summary = "Get exam by id", tags = "Exam")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Exam with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @GetMapping("/getById/{id}")
    public ExamDTO getExamById(@PathVariable Long id){
        return examMapper.examToExamDTO(examService.getExamById(id));
    }

    @Operation(summary = "Assign rating", tags = "Exam")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exam rating assigned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamRatingDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Student with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Exam with provided Id doesnt exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @PostMapping("/{examId}/assign-rating")
    public ExamRatingDTO assignExamRating(@PathVariable Long examId, @RequestParam Long studentId, @RequestParam int rating) {
       return examRatingMapper.examRatingToExamRatingDTO(examService.assignExamRating(studentId, examId, rating));
    }
}
