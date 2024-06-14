package com.practice.spring_java.domain.recruit.entity;

import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Template extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private long id;

    private String title;
    private boolean isMail; //메일 발송 여부
    private boolean isMessage; //문자 발송 여부
    private String mailTitle; //메일 제목
    private String mailContent; //메일 내용
    private String messageTitle; //문자 내용
    private String messageContent; //문자 내용
    private boolean isTempSave; //임시저장 여부
    private long registerEmployeeId;
    private long lastModifyEmployeeId;
    private boolean isDeleted;

    @Builder
    private Template(String title, boolean isMail, boolean isMessage, String mailTitle, String mailContent,
                     String messageTitle, String messageContent, long registerEmployeeId, long lastModifyEmployeeId) {
        this.title = title;
        this.isMail = isMail;
        this.isMessage = isMessage;
        this.mailTitle = mailTitle;
        this.mailContent = mailContent;
        this.messageTitle = messageTitle;
        this.messageContent = messageContent;
        this.isTempSave = false;
        this.registerEmployeeId = registerEmployeeId;
        this.lastModifyEmployeeId = lastModifyEmployeeId;
        this.isDeleted = false;
    }
}