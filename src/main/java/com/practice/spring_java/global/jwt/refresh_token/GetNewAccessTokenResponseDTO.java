package com.practice.spring_java.global.jwt.refresh_token;

public record GetNewAccessTokenResponseDTO(
        String token
) {

    public static GetNewAccessTokenResponseDTO fromEntity(String token) {
        return new GetNewAccessTokenResponseDTO(
                token
        );
    }
}
