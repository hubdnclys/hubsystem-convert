package com.practice.spring_java.domain.employee.controller;

import com.practice.spring_java.domain.employee.dto.request.VerifyEmailRequestDTO;
import com.practice.spring_java.domain.employee.dto.response.VerifyEmailResponseDTO;
import com.practice.spring_java.domain.employee.service.MailSendService;
import com.practice.spring_java.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("v1/mail")
@RequiredArgsConstructor
public class MailSendController {

    private final MailSendService mailSendService;

    @PostMapping("")
    public ResponseEntity<ResponseDTO<Void>> verifyMail(
            @Valid @RequestBody VerifyEmailRequestDTO verifyEmailRequestDTO) throws IOException {

        mailSendService.verifyMail(verifyEmailRequestDTO);
        ResponseDTO<Void> response
                = ResponseDTO.ok();
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @GetMapping("/verify-code")
    public ResponseEntity<ResponseDTO<VerifyEmailResponseDTO>> checkMailCode(
            @RequestParam("code") String code) {

        VerifyEmailResponseDTO isValid = mailSendService.checkVerifyMailCode(code);
        ResponseDTO<VerifyEmailResponseDTO> response
                = ResponseDTO.okWithData(isValid);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }
}
