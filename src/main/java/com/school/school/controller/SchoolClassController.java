package com.school.school.controller;

import com.school.school.exceptions.ErrorMessage;
import com.school.school.mapper.SchoolClassMapper;
import com.school.school.model.ExamDTO;
import com.school.school.model.SchoolClass;
import com.school.school.model.SchoolClassDTO;
import com.school.school.service.SchoolClassService;
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
@RequestMapping("/school-classes")
@RequiredArgsConstructor
public class SchoolClassController {
    private final SchoolClassService schoolClassService;
    private final SchoolClassMapper schoolClassMapper;

    @Operation(summary = "Add SchoolClass", tags = "School Class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "School class added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "School class with provided Id doesn't exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @PostMapping()
    public SchoolClassDTO addSchoolClass(@RequestBody SchoolClassDTO schoolClassDTO){
        return schoolClassMapper.schoolClassToSchoolClassDTO(schoolClassService.addSchoolClass(schoolClassDTO));
    }

    @Operation(summary = "Delete School Class", tags = "School Class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "School Class deleted",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "School Class with provided Id doesn't exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, check data and try again",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @DeleteMapping("/{id}")
    public void deleteSchoolClassById(@PathVariable Long id){
        schoolClassService.deleteSchoolById(id);
    }

    @Operation(summary = "School Class update", tags = "School Class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "School Class updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "School Class with provided Id doesn't exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, check data and try again",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @PutMapping("/{id}")
    public SchoolClassDTO updateSchoolClassById(@PathVariable Long id, @RequestBody SchoolClassDTO schoolClassDTO){
        return schoolClassMapper.schoolClassToSchoolClassDTO(schoolClassService.updateSchoolById(id, schoolClassDTO));
    }

    @Operation(summary = "Get School Classes", tags = "School Class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "School Class returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "School Class with provided Id doesn't exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, check data and try again",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @GetMapping()
    public Page<SchoolClassDTO> getExams(@ParameterObject Pageable pageable){
        Page<SchoolClass> schoolClasses = schoolClassService.getSchoolClasses(pageable);
        return schoolClasses.map(schoolClassMapper::schoolClassToSchoolClassDTO);
    }

    @Operation(summary = "Get School Class by id", tags = "School Class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "School Class returned",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExamDTO.class))}),
            @ApiResponse(responseCode = "404", description = "School Class with provided Id doesn't exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, check data and try again",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)) }),
    })
    @GetMapping("/getById/{id}")
    public SchoolClassDTO getSchoolClassById(@PathVariable Long id){
        return schoolClassMapper.schoolClassToSchoolClassDTO(schoolClassService.getSchoolClassById(id));
    }
}
