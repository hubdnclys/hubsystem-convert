package com.practice.spring_java.domain.employee.controller;

import com.practice.spring_java.domain.employee.dto.request.SignUpRequestDTO;
import com.practice.spring_java.domain.employee.dto.request.UpdateEmployeeInfoRequestDTO;
import com.practice.spring_java.domain.employee.dto.request.UpdatePasswordRequestDTO;
import com.practice.spring_java.domain.employee.dto.response.FindEmployeeByDepartmentResponseDTO;
import com.practice.spring_java.domain.employee.dto.response.GetEmployeeInfoResponseDTO;
import com.practice.spring_java.domain.employee.dto.response.SignUpResponseDTO;
import com.practice.spring_java.domain.employee.dto.response.UpdateEmployeeInfoResponseDTO;
import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.employee.enums.Department;
import com.practice.spring_java.domain.employee.service.EmployeeService;
import com.practice.spring_java.global.security.PrincipalDetails;
import com.practice.spring_java.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO<SignUpResponseDTO>> signup(
            @Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        SignUpResponseDTO signUpResponseDTO = employeeService.signup(signUpRequestDTO);

        ResponseDTO<SignUpResponseDTO> response = ResponseDTO.okWithData(signUpResponseDTO);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @GetMapping("/info")
    public ResponseEntity<ResponseDTO<GetEmployeeInfoResponseDTO>> getUserInfo(
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Employee employee = principalDetails.getUser();

        GetEmployeeInfoResponseDTO getUserInfoResponseDTO = GetEmployeeInfoResponseDTO.fromEntity(employee);
        ResponseDTO<GetEmployeeInfoResponseDTO> response = ResponseDTO.okWithData(getUserInfoResponseDTO);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }


    @GetMapping("")
    public ResponseEntity<ResponseDTO<GetEmployeeInfoResponseDTO>> findEmployeeEmailByName(
            @RequestParam("name") String employeeName) {

        GetEmployeeInfoResponseDTO getUserInfoResponseDTO =
                employeeService.findEmployeeEmailByName(employeeName);

        ResponseDTO<GetEmployeeInfoResponseDTO> response
                = ResponseDTO.okWithData(getUserInfoResponseDTO);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @PatchMapping("")
    public ResponseEntity<ResponseDTO<UpdateEmployeeInfoResponseDTO>> updateEmployeeInfo(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @Valid @RequestBody UpdateEmployeeInfoRequestDTO updateEmployeeInfoRequestDTO) {

        UpdateEmployeeInfoResponseDTO updateEmployeeInfoResponseDTO =
                employeeService.updateEmployeeInfo(principalDetails, updateEmployeeInfoRequestDTO);

        ResponseDTO<UpdateEmployeeInfoResponseDTO> response
                = ResponseDTO.okWithData(updateEmployeeInfoResponseDTO);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @PatchMapping("/password")
    public ResponseEntity<ResponseDTO<Void>> updateEmployeePassword(
            @Valid @RequestBody UpdatePasswordRequestDTO updateEmployeeInfoRequestDTO) {

        employeeService.updateEmployeePassword(updateEmployeeInfoRequestDTO);

        ResponseDTO<Void> response
                = ResponseDTO.okWithNoContentType();
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }

    @GetMapping("/department")
    public ResponseEntity<ResponseDTO<List<FindEmployeeByDepartmentResponseDTO>>> findEmployeeByDepartment(
            @RequestParam("name") Department departmentName) {

        List<FindEmployeeByDepartmentResponseDTO> findEmployeeByDepartmentResponseDTO =
                employeeService.findEmployeeByDepartment(departmentName);

        ResponseDTO<List<FindEmployeeByDepartmentResponseDTO>> response
                = ResponseDTO.okWithData(findEmployeeByDepartmentResponseDTO);
        return ResponseEntity
                .status(response.getCode())
                .body(response);
    }
}
