package com.practice.spring_java.global.jwt.refresh_token;

import com.practice.spring_java.global.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/access-token")
    public ResponseEntity<ResponseDTO<GetNewAccessTokenResponseDTO>>
    getNewAccessToken(@RequestBody RefreshToken refreshToken) {

        GetNewAccessTokenResponseDTO getNewAccessTokenResponseDTO =
                tokenService.generateAccessToken(refreshToken);

        ResponseDTO<GetNewAccessTokenResponseDTO> response
                = ResponseDTO.okWithData(getNewAccessTokenResponseDTO);

        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }
}