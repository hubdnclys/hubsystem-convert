package com.practice.spring_java.domain.applicant.service;

import com.practice.spring_java.domain.applicant.dto.request.GenerateApplicantRequestDTO;
import com.practice.spring_java.domain.applicant.dto.request.GenerateInterviewRequestDTO;
import com.practice.spring_java.domain.applicant.dto.request.RegisterApplicantToEmployeeRequestDTO;
import com.practice.spring_java.domain.applicant.dto.response.*;
import com.practice.spring_java.domain.applicant.entity.Applicant;
import com.practice.spring_java.domain.applicant.entity.ApplicantInterview;
import com.practice.spring_java.domain.applicant.enums.RecruitmentStage;
import com.practice.spring_java.domain.applicant.exception.NonExistApplicantException;
import com.practice.spring_java.domain.applicant.repository.*;
import com.practice.spring_java.domain.employee.dto.request.SignUpRequestDTO;
import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.employee.enums.Department;
import com.practice.spring_java.domain.employee.enums.EmployeeRank;
import com.practice.spring_java.domain.employee.exception.*;
import com.practice.spring_java.domain.employee.repository.EmployeeRepository;
import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.practice.spring_java.domain.recruit.enums.RecruitDepartment;
import com.practice.spring_java.domain.recruit.exception.NonExistRecruitException;
import com.practice.spring_java.domain.recruit.repository.RecruitPaperQuestionRepository;
import com.practice.spring_java.domain.recruit.repository.RecruitRepository;
import com.practice.spring_java.global.exception.UserNotFoundException;
import com.practice.spring_java.global.security.PrincipalDetails;
import com.practice.spring_java.global.util.RegexValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.practice.spring_java.domain.recruit.enums.RecruitDepartment.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicantService {

    private final RecruitRepository recruitRepository;
    private final ApplicantRepository applicantRepository;
    private final ApplicantRepositoryCustomImpl applicantRepositoryCustom;
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ApplicantAnswerRepository applicantAnswerRepository;
    private final RecruitPaperQuestionRepository recruitPaperQuestionRepository;
    private final ApplicantInterviewRepository applicantInterviewRepository;
    private final ApplicantInterviewRepositoryCustomImpl applicantInterviewRepositoryCustom;

    public void registerApplicant(
            GenerateApplicantRequestDTO generateApplicantRequestDTO) {

        Recruit recruit =
                recruitRepository.findById(generateApplicantRequestDTO.recruitId())
                        .orElseThrow(NonExistRecruitException::new);

        applicantRepository.save(generateApplicantRequestDTO.toEntity(recruit));
    }

    public List<GetAllApplicantResponseDTO> getAllApplicant(Pageable pageable) {

        return applicantRepository.findAll(pageable)
                .stream()
                .map(GetAllApplicantResponseDTO::fromEntity)
                .toList();
    }

    public void deleteApplicant(long applicantId) {

        applicantRepository.findById(applicantId).ifPresentOrElse(
                applicant -> {
                    applicantRepository.deleteById(applicantId);
                },
                () -> {
                    throw new NonExistApplicantException();
                }
        );
    }

    public List<GetAllApplicantByRecruitmentStageResponseDTO>
    getAllApplicantByRecruitmentStage() {

        List<GetAllApplicantByRecruitmentStageResponseDTO>
                getAllApplicantByRecruitmentStageResponseDTOList = new ArrayList<>();

        Stream.of(
                RecruitmentStage.APPLICATION_SUBMISSION,
                RecruitmentStage.TEST,
                RecruitmentStage.INTERVIEW,
                RecruitmentStage.FINAL_ACCEPTANCE,
                RecruitmentStage.FINAL_REJECTION
        ).forEach(stage -> getApplicantByRecruitmentStage(getAllApplicantByRecruitmentStageResponseDTOList, stage));

        return getAllApplicantByRecruitmentStageResponseDTOList;
    }

    private void getApplicantByRecruitmentStage(
            List<GetAllApplicantByRecruitmentStageResponseDTO> getAllApplicantByRecruitmentStageResponseDTOList,
            RecruitmentStage recruitmentStage) {
        List<Applicant> applicant =
                applicantRepositoryCustom.
                        findOnRecruitmentStageApplicant(recruitmentStage);

        getAllApplicantByRecruitmentStageResponseDTOList.add(
                GetAllApplicantByRecruitmentStageResponseDTO.fromEntity(
                        recruitmentStage,
                        applicant.stream().
                                map(GetApplicantByRecruitmentStageResponseDTO::fromEntity)
                                .toList())
        );
    }

    public void registerApplicantToEmployee(
            PrincipalDetails principalDetails,
            RegisterApplicantToEmployeeRequestDTO registerApplicantToEmployeeRequestDTO) {

        Applicant applicant = applicantValidator(registerApplicantToEmployeeRequestDTO);

        String encodedPassword = passwordEncoder.encode(
                registerApplicantToEmployeeRequestDTO.password());

        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO(
                applicant.getEmail(),
                encodedPassword,
                applicant.getName(),
                applicant.getPhone(),
                EmployeeRank.STAFF,
                getDepartment(applicant.getDepartment())
        );

        employeeRepository.save(
                signUpRequestDTO.toEntity(encodedPassword)
        );
    }

    private Applicant applicantValidator(
            RegisterApplicantToEmployeeRequestDTO registerApplicantToEmployeeRequestDTO) {
        Applicant applicant =
                applicantRepository.findById(registerApplicantToEmployeeRequestDTO.applicantId())
                        .orElseThrow(NonExistApplicantException::new);

        employeeRepository.findByEmail(applicant.getEmail()).ifPresent(user -> {
            throw new EmailDuplicateException();
        });

        if (!RegexValidator.isValidEmail(applicant.getEmail())) {
            throw new InvalidEmailException();
        }
        if (!RegexValidator.isValidName(applicant.getName())) {
            throw new InvalidNameTypeException();
        }
        if (!RegexValidator.isValidPassword(registerApplicantToEmployeeRequestDTO.password())) {
            throw new InvalidPassWordException();
        }
        if (!RegexValidator.isValidPhone(applicant.getPhone())) {
            throw new InvalidPhoneTypeException();
        }
        return applicant;
    }

    private Department getDepartment(
            RecruitDepartment recruitDepartment) {

        if (recruitDepartment == PROJECT_MANAGE) {
            return Department.PROJECT_MANAGE;
        } else if (recruitDepartment == PROGRAMMING) {
            return Department.PROGRAMMING;
        } else if (recruitDepartment == DESIGN) {
            return Department.DESIGN;
        } else {
            return Department.PEOPLE;
        }
    }

    public GetApplicantDetailResponseDTO getApplicantDetail(long applicantId) {

        Applicant applicant =
                applicantRepository.findById(applicantId).orElseThrow(
                        NonExistApplicantException::new
                );

        List<GetApplicantQuestionAnswerResponseDTO>
                getApplicantQuestionAnswerResponseDTOList = new ArrayList<>();

        applicantAnswerRepository.findByApplicantId(applicantId).forEach(
                applicantAnswer -> {
                    getApplicantQuestionAnswerResponseDTOList.add(
                            GetApplicantQuestionAnswerResponseDTO.fromEntity(
                                    applicantAnswer,
                                    recruitPaperQuestionRepository.
                                            findById(applicantAnswer.
                                                    getRecruitPaperQuestionId()).get()
                            ));
                }
        );

        return GetApplicantDetailResponseDTO.fromEntity(
                applicant, getApplicantQuestionAnswerResponseDTOList
        );
    }

    public void registerRequestInterviewStatus(
            long applicantId, GenerateInterviewRequestDTO generateInterviewRequestDTO) {

        Applicant applicant =
                applicantRepository.findById(applicantId)
                        .orElseThrow(NonExistApplicantException::new);

        Employee employee =
                employeeRepository.findById(generateInterviewRequestDTO.interviewerId())
                        .orElseThrow(UserNotFoundException::new);

        applicantInterviewRepository.save(
                generateInterviewRequestDTO.toEntity(applicant, employee));
    }

    public List<GetInterviewLogResponseDTO> getInterviewRequestLog(
            long applicantId) {

        Applicant applicant =
                applicantRepository.findById(applicantId).orElseThrow(
                        NonExistApplicantException::new
                );

        return applicantInterviewRepository
                .findByApplicantId(applicantId)
                .stream()
                .map(applicantInterview ->
                        GetInterviewLogResponseDTO.fromEntity(
                                applicant, applicantInterview))
                .toList();
    }

    public List<GetInterviewScheduleResponseDTO> getInterviewSchedule(LocalDate date) {

        List<ApplicantInterview> applicantInterviewList =
                applicantInterviewRepositoryCustom
                        .findApplicantInterviewByDate(date);

        return applicantInterviewList
                .stream()
                .map(GetInterviewScheduleResponseDTO::fromEntity)
                .toList();
    }
}

