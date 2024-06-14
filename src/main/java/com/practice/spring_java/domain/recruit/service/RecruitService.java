package com.practice.spring_java.domain.recruit.service;

import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.employee.repository.EmployeeRepository;
import com.practice.spring_java.domain.recruit.dto.request.RegisterRecruitPaperRequestDTO;
import com.practice.spring_java.domain.recruit.dto.request.RegisterRecruitRequestDTO;
import com.practice.spring_java.domain.recruit.dto.request.RegisterTemplateRequestDTO;
import com.practice.spring_java.domain.recruit.dto.response.*;
import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.practice.spring_java.domain.recruit.entity.RecruitBenefits;
import com.practice.spring_java.domain.recruit.entity.RecruitPaper;
import com.practice.spring_java.domain.recruit.entity.Template;
import com.practice.spring_java.domain.recruit.exception.NonExistRecruitPaperException;
import com.practice.spring_java.domain.recruit.exception.NonExistTemplateException;
import com.practice.spring_java.domain.recruit.repository.*;
import com.practice.spring_java.global.exception.UserNotFoundException;
import com.practice.spring_java.global.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final RecruitBenefitsRepository recruitBenefitsRepository;
    private final UploadFileRepository uploadFileRepository;
    private final RecruitStepRepository recruitStepRepository;
    private final EmployeeRepository employeeRepository;
    private final RecruitPaperRepository recruitPaperRepository;
    private final RecruitPaperQuestionRepository recruitPaperQuestionRepository;
    private final TemplateRepository templateRepository;
    private final RecruitLinkRepository recruitLinkRepository;
    private final RecruitRepositoryCustomImpl recruitRepositoryCustom;

    public void postRecruit(
            PrincipalDetails principalDetails,
            RegisterRecruitRequestDTO registerRecruitRequestDTO) {

        Employee employee = principalDetails.getUser();

        validateInterviewer(registerRecruitRequestDTO);

        Recruit recruit = saveRecruit(employee, registerRecruitRequestDTO);
        saveRecruitBenefits(registerRecruitRequestDTO, recruit);
        saveRecruitSteps(registerRecruitRequestDTO, recruit);
        saveRecruitLinks(registerRecruitRequestDTO, recruit);
    }

    private void validateInterviewer(RegisterRecruitRequestDTO registerRecruitRequestDTO) {
        employeeRepository.findById(registerRecruitRequestDTO.interviewerIdx())
                .orElseThrow(UserNotFoundException::new);
    }

    private Recruit saveRecruit(Employee employee, RegisterRecruitRequestDTO registerRecruitRequestDTO) {
        return recruitRepository.save(registerRecruitRequestDTO.toEntity(employee, employee.getId()));
    }

    private void saveRecruitBenefits(RegisterRecruitRequestDTO registerRecruitRequestDTO, Recruit recruit) {
        registerRecruitRequestDTO.recruitBenefitsList().forEach(benefit -> {
            RecruitBenefits recruitBenefits = recruitBenefitsRepository.save(benefit.toEntity(recruit));
            benefit.recruitBenefits().forEach(recruitBenefit ->
                    uploadFileRepository.save(recruitBenefit.toEntity(recruitBenefits))
            );
        });
    }

    private void saveRecruitSteps(RegisterRecruitRequestDTO registerRecruitRequestDTO, Recruit recruit) {
        registerRecruitRequestDTO.recruitStepList().forEach(step -> {
            recruitPaperRepository.findById(step.recruitPaperNo())
                    .orElseThrow(NonExistRecruitPaperException::new);

            templateRepository.findById(step.alarmTemplateNo())
                    .orElseThrow(NonExistTemplateException::new);
            templateRepository.findById(step.rejectTemplateNo())
                    .orElseThrow(NonExistTemplateException::new);

            recruitStepRepository.save(step.toEntity(recruit));
        });
    }

    private void saveRecruitLinks(RegisterRecruitRequestDTO registerRecruitRequestDTO, Recruit recruit) {
        registerRecruitRequestDTO.linkList().forEach(link ->
                recruitLinkRepository.save(link.toEntity(recruit))
        );
    }

    public RegisterRecruitPaperResponseDTO postRecruitPaper
            (PrincipalDetails principalDetails, RegisterRecruitPaperRequestDTO registerRecruitPaperRequestDTO) {

        Employee employee = principalDetails.getUser();

        RecruitPaper recruitPaper =
                recruitPaperRepository.save(
                        registerRecruitPaperRequestDTO.toEntity(employee));

        registerRecruitPaperRequestDTO.questionList().forEach(
                question -> recruitPaperQuestionRepository.save(
                        question.toEntity(recruitPaper)
                )
        );

        return RegisterRecruitPaperResponseDTO
                .fromEntity(recruitPaper.getId());
    }

    public RegisterTemplateResponseDTO postTemplate
            (PrincipalDetails principalDetails, RegisterTemplateRequestDTO registerTemplateRequestDTO) {

        Template template =
                templateRepository.save(registerTemplateRequestDTO
                        .toEntity(principalDetails.getUser()));

        return RegisterTemplateResponseDTO.fromEntity(template.getId());
    }

    public GetRecruitInfoResponseDTO getRecruitInfoById(long recruitId) {
        //채용 공고가 유효한지 확인
        Recruit recruit = getRecruitById(recruitId);

        return GetRecruitInfoResponseDTO.fromEntity(
                recruit, mapToLinkList(recruit), mapToBenefitList(recruit), mapToStepList(recruit));
    }

    private Recruit getRecruitById(long recruitId) {
        return recruitRepository.findById(recruitId)
                .orElseThrow(NonExistRecruitPaperException::new);
    }

    private List<Recruit> getRecruitByRecruitTitle(String recruitTitle) {
        return recruitRepositoryCustom.findRecruitByRecruitTitle(recruitTitle);
    }

    //공고의 링크들
    private List<GetRecruitLinkListResponseDTO> mapToLinkList(Recruit recruit) {
        return recruit.getRecruitLinkList().stream()
                .map(GetRecruitLinkListResponseDTO::fromEntity)
                .toList();
    }

    //공고의 채용혜택들
    private List<GetRecruitBenefitListResponseDTO> mapToBenefitList(Recruit recruit) {
        return recruit.getRecruitBenefitList().stream()
                .map(this::mapToBenefitResponseDTO)
                .toList();
    }

    private GetRecruitBenefitListResponseDTO mapToBenefitResponseDTO(RecruitBenefits recruitBenefits) {
        List<GetUploadFileListResponseDTO> uploadFileListResponseDTOList = recruitBenefits.getUploadFile().stream()
                .map(GetUploadFileListResponseDTO::fromEntity)
                .toList();
        return GetRecruitBenefitListResponseDTO.fromEntity(recruitBenefits, uploadFileListResponseDTOList);
    }

    //공고의 채용단계
    private List<GetRecruitStepListResponseDTO> mapToStepList(Recruit recruit) {
        return recruit.getRecruitStepList().stream()
                .map(GetRecruitStepListResponseDTO::fromEntity)
                .toList();
    }

    public List<GetRecruitInfoResponseDTO> getRecruitInfoByRecruitTitle(
            String recruitTitle) {

        List<Recruit> recruits = getRecruitByRecruitTitle(recruitTitle);
        List<GetRecruitInfoResponseDTO> getRecruitInfoResponseDTOList = new ArrayList<>();

        recruits.forEach(
                recruit -> {
                    getRecruitInfoResponseDTOList.add(
                            GetRecruitInfoResponseDTO.fromEntity(
                                    recruit, mapToLinkList(recruit),
                                    mapToBenefitList(recruit),
                                    mapToStepList(recruit)));
                }
        );

        return getRecruitInfoResponseDTOList;
    }
}