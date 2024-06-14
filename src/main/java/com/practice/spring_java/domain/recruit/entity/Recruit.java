package com.practice.spring_java.domain.recruit.entity;

import com.practice.spring_java.domain.applicant.entity.Applicant;
import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.recruit.enums.EmploymentType;
import com.practice.spring_java.domain.recruit.enums.RecruitDepartment;
import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recruit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id")
    private long id;

    @Column(length = 500)
    private String title;

    private LocalDate startDate;
    private LocalDate endDate;
    private boolean permanentRecruitment; //상시채용여부

    private RecruitDepartment department;
    private boolean isNew; //신입 채용 여부
    private boolean isCareer; //경력 채용 여부

    private int minCareer; //최소 연차
    private int maxCareer; //최대 연차

    private String workspaceName; //근무지명
    private EmploymentType employmentType; //고용형태
    private long interviewerIdx; // 인터뷰 담당자
    private String postcode; //우편번호
    private String address; //주소
    private String detailAddress; //상세주소
    private String detail; //상세내역
    private String recruitInfo; //자격 요건
    private String treatmentInfo;//우대시항
    private int applicationCount; //지원자 수
    private boolean isTempSave; ///임시저장 여부
    private boolean isStop; //공고 중단 여부
    private boolean isHidden; //비공개 여부
    private boolean isClosed;//조기종료 여부

    private boolean resumeHasCareer; // 지원서 경력 여부
    private boolean resumeDesiredPay; //희망 연봉 여부
    private boolean resumeLanguage; //언어 여부
    private boolean resumeSubmitted; //이력서 제출 여부
    private boolean resumePortfolioSubmitted; //포트폴리오 제출 여부
    private boolean resumeQuestionFiled; //이력서 질문지 여부

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee registerEmployee;

    private long modifyEmployeeId; //최종 수정자

    private boolean isDeleted; //삭제 여부

    //공고가 올라간 링크
    @OneToMany(mappedBy = "recruit", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RecruitLink> recruitLinkList = new ArrayList<>();

    //채용혜택
    @OneToMany(mappedBy = "recruit", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RecruitBenefits> recruitBenefitList = new ArrayList<>();

    //채용의 절차
    @OneToMany(mappedBy = "recruit", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RecruitStep> recruitStepList = new ArrayList<>();

    @OneToMany(mappedBy = "recruit", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Applicant> applicantList = new ArrayList<>();

    @Builder
    private Recruit(String title, LocalDate startDate, LocalDate endDate, boolean permanentRecruitment, RecruitDepartment recruitDepartment, boolean isNew, boolean isCareer, int minCareer,
                    int maxCareer, String workspaceName, EmploymentType employmentType, long interviewerIdx, String postcode, String address, String detailAddress,
                    String detail, String recruitInfo, String treatmentInfo, Employee registerEmployee, long modifyEmployee,
                    boolean resumeHasCareer, boolean resumeDesiredPay, boolean resumeLanguage,
                    boolean resumeSubmitted, boolean resumePortfolioSubmitted, boolean resumeQuestionFiled) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.permanentRecruitment = permanentRecruitment;
        this.department = recruitDepartment;
        this.isNew = isNew;
        this.isCareer = isCareer;
        this.minCareer = minCareer;
        this.maxCareer = maxCareer;
        this.workspaceName = workspaceName;
        this.employmentType = employmentType;
        this.interviewerIdx = interviewerIdx;
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.detail = detail;
        this.recruitInfo = recruitInfo;
        this.treatmentInfo = treatmentInfo;
        this.applicationCount = 0;
        this.isTempSave = false;
        this.isStop = false;
        this.isHidden = false;
        this.isClosed = false;
        this.registerEmployee = registerEmployee;
        this.modifyEmployeeId = modifyEmployee;
        this.resumeHasCareer = resumeHasCareer;
        this.resumeDesiredPay = resumeDesiredPay;
        this.resumeLanguage = resumeLanguage;
        this.resumeSubmitted = resumeSubmitted;
        this.resumePortfolioSubmitted = resumePortfolioSubmitted;
        this.resumeQuestionFiled = resumeQuestionFiled;
        this.isDeleted = false;
    }
}