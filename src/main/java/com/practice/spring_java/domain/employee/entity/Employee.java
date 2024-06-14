package com.practice.spring_java.domain.employee.entity;

import com.practice.spring_java.domain.applicant.entity.ApplicantInterview;
import com.practice.spring_java.domain.employee.dto.request.UpdateEmployeeInfoRequestDTO;
import com.practice.spring_java.domain.employee.enums.Department;
import com.practice.spring_java.domain.employee.enums.EmployeeRank;
import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Employee extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id; //사번

    private String profileImage;

    @Column(nullable = false, length = 110)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(length = 20)
    private String phone;

    @Column(nullable = false)
    private EmployeeRank employeeRank; //직급

    @Column(nullable = false, columnDefinition = "int default 0")
    private int positionNo; //직책

    private int pay; //연봉

    private String authority;

    private boolean isDeleted;

    private Department department;

    @OneToMany(mappedBy = "registerEmployee", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Recruit> recruitList = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<ApplicantInterview> applicantInterviewList = new ArrayList<>();

    @Builder
    private Employee(String profileImage, String email, String password, String name, String phone, EmployeeRank employeeRank, String authority, Department department) {
        this.profileImage = profileImage;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.employeeRank = employeeRank;
        this.authority = authority;
        this.isDeleted = false;
        this.department = department;
    }

    public void update(UpdateEmployeeInfoRequestDTO updateEmployeeInfoRequestDTO, String encodedPassword) {
        this.profileImage = updateEmployeeInfoRequestDTO.image();
        this.password = encodedPassword;
        this.phone = updateEmployeeInfoRequestDTO.phone();
    }

    public void update(String encodedPassword) {
        this.password = encodedPassword;
    }
}