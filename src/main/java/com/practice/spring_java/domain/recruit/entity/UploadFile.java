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
//채용혜택 관련 이미지
public class UploadFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_file_id")
    private long id;

    private String originName; //파일명

    private String saveName; //저장된 파일명

    private String path; //경로

    private String extension; //확장자

    private int size;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruit_benefit_id")
    private RecruitBenefits recruitBenefits;

    @Builder
    private UploadFile(
            String originName, String saveName, String path, String extension, int size,
            RecruitBenefits recruitBenefits) {

        this.originName = originName;
        this.saveName = saveName;
        this.path = path;
        this.extension = extension;
        this.size = size;
        this.recruitBenefits = recruitBenefits;
    }
}