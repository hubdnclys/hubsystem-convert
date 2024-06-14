package com.practice.spring_java.domain.recruit.dto.request;

import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.recruit.entity.RecruitPaper;
import com.practice.spring_java.domain.recruit.enums.RecruitPaperType;

import java.util.List;

//전형절차 추가 request
public record RegisterRecruitPaperRequestDTO(

        String title, //전형 이름
        RecruitPaperType recruitPaperType, //전형 종류
        List<RegisterRecruitPaperQuestionRequestDTO> questionList

) {
    public RecruitPaper toEntity(Employee employee) {
        return RecruitPaper.builder()
                .title(title)
                .testType(recruitPaperType)
                .registerEmployeeId(employee.getId())
                .lastModifyEmployeeId(employee.getId())
                .build();
    }
}