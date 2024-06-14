package com.practice.spring_java.domain.employee.service;

import com.practice.spring_java.domain.employee.dto.request.SignUpRequestDTO;
import com.practice.spring_java.domain.employee.dto.request.UpdateEmployeeInfoRequestDTO;
import com.practice.spring_java.domain.employee.dto.request.UpdatePasswordRequestDTO;
import com.practice.spring_java.domain.employee.dto.response.FindEmployeeByDepartmentResponseDTO;
import com.practice.spring_java.domain.employee.dto.response.GetEmployeeInfoResponseDTO;
import com.practice.spring_java.domain.employee.dto.response.SignUpResponseDTO;
import com.practice.spring_java.domain.employee.dto.response.UpdateEmployeeInfoResponseDTO;
import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.employee.enums.Department;
import com.practice.spring_java.domain.employee.exception.*;
import com.practice.spring_java.domain.employee.repository.EmployeeRepository;
import com.practice.spring_java.global.exception.UserNotFoundException;
import com.practice.spring_java.global.security.PrincipalDetails;
import com.practice.spring_java.global.util.RegexValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public SignUpResponseDTO signup(SignUpRequestDTO signUpRequestDTO) {
        checkEmailDuplication(signUpRequestDTO.email());

        if (!RegexValidator.isValidEmail(signUpRequestDTO.email())) {
            throw new InvalidEmailException();
        }
        if (!RegexValidator.isValidName(signUpRequestDTO.name())) {
            throw new InvalidNameTypeException();
        }
        if (!RegexValidator.isValidPassword(signUpRequestDTO.password())) {
            throw new InvalidPassWordException();
        }
        if (!RegexValidator.isValidPhone(signUpRequestDTO.phone())) {
            throw new InvalidPhoneTypeException();
        }

        Employee user = signUpRequestDTO.toEntity(passwordEncoder.encode(signUpRequestDTO.password()));
        employeeRepository.save(user);

        return SignUpResponseDTO.fromEntity(user);
    }

    public void checkEmailDuplication(String email) {
        employeeRepository.findByEmail(email).ifPresent(user -> {
            throw new EmailDuplicateException();
        });
    }

    public GetEmployeeInfoResponseDTO findEmployeeEmailByName(
            String employeeName) {

        Employee employee = employeeRepository.findByName(employeeName)
                .orElseThrow(InvalidNameException::new);

        return GetEmployeeInfoResponseDTO.fromEntity(employee);
    }

    public UpdateEmployeeInfoResponseDTO updateEmployeeInfo(
            PrincipalDetails principalDetails, UpdateEmployeeInfoRequestDTO updateEmployeeInfoRequestDTO) {

        Employee employee = principalDetails.getUser();

        if (!RegexValidator.isValidPassword(updateEmployeeInfoRequestDTO.password())) {
            throw new InvalidPassWordException();
        }
        if (!RegexValidator.isValidPhone(updateEmployeeInfoRequestDTO.phone())) {
            throw new InvalidPhoneTypeException();
        }

        employee.update(
                updateEmployeeInfoRequestDTO, passwordEncoder.encode(updateEmployeeInfoRequestDTO.password()));
        Employee updated = employeeRepository.save(employee);

        return UpdateEmployeeInfoResponseDTO.fromEntity(updated);
    }

    public void updateEmployeePassword(UpdatePasswordRequestDTO updateEmployeeInfoRequestDTO) {

        Employee employee =
                employeeRepository.findByEmail(updateEmployeeInfoRequestDTO.email())
                        .orElseThrow(UserNotFoundException::new);

        if (!employee.getName().equals(updateEmployeeInfoRequestDTO.name())) {
            throw new UserNotFoundException();
        }

        if (!RegexValidator.isValidPassword(updateEmployeeInfoRequestDTO.password())) {
            throw new InvalidPassWordException();
        }

        employee.update((passwordEncoder.encode(updateEmployeeInfoRequestDTO.password())));
        employeeRepository.save(employee);
    }

    public List<FindEmployeeByDepartmentResponseDTO>
    findEmployeeByDepartment(Department departmentName) {

        List<Employee> employees =
                employeeRepository.findAllByDepartment(departmentName);

        return employees.stream()
                .map(FindEmployeeByDepartmentResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}

