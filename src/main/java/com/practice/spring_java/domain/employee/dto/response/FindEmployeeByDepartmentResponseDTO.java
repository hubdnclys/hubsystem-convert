package com.practice.spring_java.domain.employee.dto.response;


import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.employee.enums.Department;
import com.practice.spring_java.domain.employee.enums.EmployeeRank;

public record FindEmployeeByDepartmentResponseDTO(

        long employeeId,
        String name,
        EmployeeRank employeeRank,
        Department department
) {

    public static FindEmployeeByDepartmentResponseDTO fromEntity(Employee employee) {
        return new FindEmployeeByDepartmentResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmployeeRank(),
                employee.getDepartment()
        );
    }
}
