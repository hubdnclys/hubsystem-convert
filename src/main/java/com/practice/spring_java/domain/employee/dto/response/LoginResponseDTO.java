package com.practice.spring_java.domain.employee.dto.response;

import com.practice.spring_java.domain.employee.entity.Employee;

public record LoginResponseDTO(
        String email,
        String name,
        String token,
        String refreshToken
) {

    public static LoginResponseDTO fromEntity(Employee user, String token, String refreshToken) {
        return new LoginResponseDTO(
                user.getEmail(),
                user.getName(),
                token,
                refreshToken
        );
    }
}
