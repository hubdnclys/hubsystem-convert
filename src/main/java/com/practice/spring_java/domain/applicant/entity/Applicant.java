package com.practice.spring_java.domain.applicant.entity;

import com.practice.spring_java.domain.applicant.enums.RecruitmentStage;
import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.practice.spring_java.domain.recruit.enums.RecruitDepartment;
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
public class Applicant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id")
    private Long id; //지원자 id

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit; //지원한 공고

    private String name;
    private String phone;
    private String email;
    private RecruitDepartment department;
    private boolean isNew; // 신입여부
    private int experienceYear; //경력년차
    private String applicationPath; //지원경로
    private RecruitmentStage recruitmentStage; //지원자의 단계
    private String resumeUrl; //이력서 url
    private String portfolioUrl; //포트폴리오 url

    //질문지 응답 목록
    @OneToMany(mappedBy = "applicant", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<ApplicantAnswer> applicantAnswersList = new ArrayList<>();

    //인터뷰 목록
    @OneToMany(mappedBy = "applicant", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<ApplicantInterview> interviewApplicant = new ArrayList<>();

    @Builder
    private Applicant(String name, String phone, String email, RecruitDepartment department,
                      boolean isNew, int experienceYear, String applicationPath,
                      RecruitmentStage recruitmentStage, String resumeUrl, String portfolioUrl,
                      Recruit recruit) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.isNew = isNew;
        this.experienceYear = experienceYear;
        this.applicationPath = applicationPath;
        this.recruitmentStage = recruitmentStage;
        this.resumeUrl = resumeUrl;
        this.portfolioUrl = portfolioUrl;
        this.recruit = recruit;
    }
}