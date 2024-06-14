package com.practice.spring_java.domain.employee.dto.request;

import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.employee.enums.Department;
import com.practice.spring_java.domain.employee.enums.EmployeeRank;
import jakarta.validation.constraints.NotNull;

public record SignUpRequestDTO(

        @NotNull(message = "email은 필수값입니다")
        String email,

        @NotNull(message = "password는 필수값입니다")
        String password,

        @NotNull(message = "name은 필수값입니다")
        String name,

        @NotNull(message = "phone은 필수값입니다")
        String phone,

        @NotNull(message = "rank은 필수값입니다")
        EmployeeRank rank,

        @NotNull(message = "Department은 필수값입니다")
        Department department
) {

    public Employee toEntity(String encodedPassword) {
        return Employee.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .authority("ROLE_USER")
                .phone(phone)
                .employeeRank(rank)
                .department(department)
                .build();
    }
}
