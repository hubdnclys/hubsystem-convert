package com.practice.spring_java.domain.employee.dto.response;


import com.practice.spring_java.domain.employee.entity.Employee;

public record GetEmployeeInfoResponseDTO(

        String email,
        String name,
        String authority
) {

    public static GetEmployeeInfoResponseDTO fromEntity(Employee employee) {
        return new GetEmployeeInfoResponseDTO(
                employee.getEmail(),
                employee.getName(),
                employee.getAuthority()
        );
    }
}
