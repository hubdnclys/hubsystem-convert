package com.practice.spring_java.domain.applicant.controller;

import com.practice.spring_java.domain.applicant.dto.request.GenerateApplicantRequestDTO;
import com.practice.spring_java.domain.applicant.dto.request.GenerateInterviewRequestDTO;
import com.practice.spring_java.domain.applicant.dto.request.RegisterApplicantToEmployeeRequestDTO;
import com.practice.spring_java.domain.applicant.dto.response.*;
import com.practice.spring_java.domain.applicant.service.ApplicantService;
import com.practice.spring_java.global.security.PrincipalDetails;
import com.practice.spring_java.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/applicant")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO<Void>> registerApplicant(
            @Valid @RequestBody GenerateApplicantRequestDTO generateApplicantRequestDTO) {

        applicantService.registerApplicant(generateApplicantRequestDTO);

        ResponseDTO<Void> response = ResponseDTO.okWithCreated();
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<GetAllApplicantResponseDTO>>> getAllApplicant(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        List<GetAllApplicantResponseDTO> getAllApplicantResponseDTOList =
                applicantService.getAllApplicant(pageable);

        ResponseDTO<List<GetAllApplicantResponseDTO>> response =
                ResponseDTO.okWithData(getAllApplicantResponseDTOList);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @DeleteMapping("/{applicantId}")
    public ResponseEntity<ResponseDTO<Void>> deleteApplicant(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable long applicantId
    ) {
        applicantService.deleteApplicant(applicantId);
        ResponseDTO<Void> response =
                ResponseDTO.okWithNoContentType();
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @GetMapping("/recruitment-stage")
    public ResponseEntity<ResponseDTO<List<GetAllApplicantByRecruitmentStageResponseDTO>>>
    getApplicantByRecruitmentStage() {
        List<GetAllApplicantByRecruitmentStageResponseDTO>
                getAllApplicantByRecruitmentStageResponseDTOList =
                applicantService.getAllApplicantByRecruitmentStage();

        ResponseDTO<List<GetAllApplicantByRecruitmentStageResponseDTO>> response =
                ResponseDTO.okWithData(getAllApplicantByRecruitmentStageResponseDTOList);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @PostMapping("/register-employee")
    public ResponseEntity<ResponseDTO<Void>> registerApplicantToEmployee(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @Valid @RequestBody RegisterApplicantToEmployeeRequestDTO registerApplicantToEmployeeRequestDTO) {

        applicantService.registerApplicantToEmployee(
                principalDetails, registerApplicantToEmployeeRequestDTO);

        ResponseDTO<Void> response = ResponseDTO.okWithCreated();
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @GetMapping("/{applicantId}")
    public ResponseEntity<ResponseDTO<GetApplicantDetailResponseDTO>> getApplicantDetail(
            @PathVariable long applicantId
    ) {
        GetApplicantDetailResponseDTO getAllApplicantResponseDTOList =
                applicantService.getApplicantDetail(applicantId);

        ResponseDTO<GetApplicantDetailResponseDTO> response =
                ResponseDTO.okWithData(getAllApplicantResponseDTOList);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @PostMapping("/{applicantId}/interview")
    public ResponseEntity<ResponseDTO<Void>> registerRequestInterviewStatus(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable long applicantId,
            @Valid @RequestBody GenerateInterviewRequestDTO generateInterviewRequestDTO
    ) {
        applicantService.
                registerRequestInterviewStatus(applicantId, generateInterviewRequestDTO);

        ResponseDTO<Void> response =
                ResponseDTO.okWithNoContentType();
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @GetMapping("/{applicantId}/interview")
    public ResponseEntity<ResponseDTO<List<GetInterviewLogResponseDTO>>> getRequestInterviewStatusLog(
            @PathVariable long applicantId
    ) {

        List<GetInterviewLogResponseDTO> generateInterviewRequestDTO =
                applicantService.getInterviewRequestLog(applicantId);

        ResponseDTO<List<GetInterviewLogResponseDTO>> response =
                ResponseDTO.okWithData(generateInterviewRequestDTO);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @GetMapping("/interview")
    public ResponseEntity<ResponseDTO<List<GetInterviewScheduleResponseDTO>>> getInterviewSchedule(
            @RequestParam("date") LocalDate date
    ) {

        List<GetInterviewScheduleResponseDTO> getInterviewScheduleResponseDTOList =
                applicantService.getInterviewSchedule(date);

        ResponseDTO<List<GetInterviewScheduleResponseDTO>> response =
                ResponseDTO.okWithData(getInterviewScheduleResponseDTOList);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }
}
