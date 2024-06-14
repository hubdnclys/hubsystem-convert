package com.practice.spring_java.domain.employee.dto.response;


import com.practice.spring_java.domain.employee.entity.Employee;

public record UpdateEmployeeInfoResponseDTO(

        String image,
        String email,
        String name,
        String phone,
        String authority
) {

    public static UpdateEmployeeInfoResponseDTO fromEntity(Employee employee) {
        return new UpdateEmployeeInfoResponseDTO(
                employee.getProfileImage(),
                employee.getEmail(),
                employee.getName(),
                employee.getPhone(),
                employee.getAuthority()
        );
    }
}
