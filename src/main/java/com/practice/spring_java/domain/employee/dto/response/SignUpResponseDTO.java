package com.practice.spring_java.domain.employee.dto.response;


import com.practice.spring_java.domain.employee.entity.Employee;

public record SignUpResponseDTO(

        Long memberId,
        String email,
        String name
) {

    public static SignUpResponseDTO fromEntity(Employee user) {
        return new SignUpResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}
