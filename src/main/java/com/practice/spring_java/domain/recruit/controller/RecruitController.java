package com.practice.spring_java.domain.recruit.controller;

import com.practice.spring_java.domain.recruit.dto.request.RegisterRecruitPaperRequestDTO;
import com.practice.spring_java.domain.recruit.dto.request.RegisterRecruitRequestDTO;
import com.practice.spring_java.domain.recruit.dto.request.RegisterTemplateRequestDTO;
import com.practice.spring_java.domain.recruit.dto.response.GetRecruitInfoResponseDTO;
import com.practice.spring_java.domain.recruit.dto.response.RegisterRecruitPaperResponseDTO;
import com.practice.spring_java.domain.recruit.dto.response.RegisterTemplateResponseDTO;
import com.practice.spring_java.domain.recruit.service.RecruitService;
import com.practice.spring_java.global.security.PrincipalDetails;
import com.practice.spring_java.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/recruit")
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO<Void>> postRecruit(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @Valid @RequestBody RegisterRecruitRequestDTO registerRecruitRequestDTO) {

        recruitService.postRecruit(principalDetails, registerRecruitRequestDTO);

        ResponseDTO<Void> response = ResponseDTO.okWithCreated();
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    //전형절차 추가
    @PostMapping("/recruit-paper")
    public ResponseEntity<ResponseDTO<RegisterRecruitPaperResponseDTO>> postRecruitPaper(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @Valid @RequestBody RegisterRecruitPaperRequestDTO registerRecruitPaperRequestDTO) {

        RegisterRecruitPaperResponseDTO registerRecruitPaperResponseDTO =
                recruitService.postRecruitPaper(principalDetails, registerRecruitPaperRequestDTO);

        ResponseDTO<RegisterRecruitPaperResponseDTO> response =
                ResponseDTO.okWithData(registerRecruitPaperResponseDTO);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    //템플릿 추가
    @PostMapping("/template")
    public ResponseEntity<ResponseDTO<RegisterTemplateResponseDTO>> postTemplate(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @Valid @RequestBody RegisterTemplateRequestDTO registerTemplateRequestDTO) {

        RegisterTemplateResponseDTO registerTemplateResponseDTO =
                recruitService.postTemplate(principalDetails, registerTemplateRequestDTO);

        ResponseDTO<RegisterTemplateResponseDTO> response =
                ResponseDTO.okWithData(registerTemplateResponseDTO);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    //템플릿 추가
    @GetMapping("/{recruitId}")
    public ResponseEntity<ResponseDTO<GetRecruitInfoResponseDTO>> getRecruitInfoById(
            @PathVariable long recruitId) {

        GetRecruitInfoResponseDTO getRecruitInfoResponseDTO =
                recruitService.getRecruitInfoById(recruitId);

        ResponseDTO<GetRecruitInfoResponseDTO> response =
                ResponseDTO.okWithData(getRecruitInfoResponseDTO);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    //공고이름으로 공고목록들 조회
    @GetMapping("/")
    public ResponseEntity<ResponseDTO<List<GetRecruitInfoResponseDTO>>> getRecruitInfoByRecruitTitle(
            @RequestParam("title") String recruitTitle) {

        List<GetRecruitInfoResponseDTO> getRecruitInfoResponseDTO =
                recruitService.getRecruitInfoByRecruitTitle(recruitTitle);

        ResponseDTO<List<GetRecruitInfoResponseDTO>> response =
                ResponseDTO.okWithData(getRecruitInfoResponseDTO);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }
}
