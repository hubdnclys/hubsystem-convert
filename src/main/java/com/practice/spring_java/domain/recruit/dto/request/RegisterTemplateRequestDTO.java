package com.practice.spring_java.domain.recruit.dto.request;

import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.recruit.entity.Template;

public record RegisterTemplateRequestDTO(

        String title,
        boolean isMail, //메일 발송 여부
        boolean isMessage, //메시지 발송 여부
        String mailTitle, //메일 제목
        String mailContent, //메일 내용
        String messageTitle,
        String messageContent
) {
    public Template toEntity(Employee employee) {
        return Template.builder()
                .title(title)
                .isMail(isMail)
                .isMessage(isMessage)
                .mailTitle(mailTitle)
                .mailContent(mailContent)
                .messageTitle(messageTitle)
                .messageContent(messageContent)
                .registerEmployeeId(employee.getId())
                .lastModifyEmployeeId(employee.getId())
                .build();
    }
}