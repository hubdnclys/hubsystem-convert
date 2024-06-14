package com.practice.spring_java.domain.applicant.entity;

import com.practice.spring_java.domain.applicant.enums.InterviewRequestStatus;
import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//지원자의 인터뷰
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ApplicantInterview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_interview_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    private LocalDateTime interviewDate;
    private InterviewRequestStatus requestChange;

    @Builder
    private ApplicantInterview(
            Employee employee, Applicant applicant, LocalDateTime interviewDate,
            InterviewRequestStatus requestChange
    ) {
        this.employee = employee;
        this.applicant = applicant;
        this.interviewDate = interviewDate;
        this.requestChange = requestChange;
    }
}