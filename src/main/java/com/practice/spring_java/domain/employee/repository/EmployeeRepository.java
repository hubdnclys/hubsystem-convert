package com.practice.spring_java.domain.employee.repository;

import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.employee.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByName(String employeeName);

    List<Employee> findAllByDepartment(Department department);

}
