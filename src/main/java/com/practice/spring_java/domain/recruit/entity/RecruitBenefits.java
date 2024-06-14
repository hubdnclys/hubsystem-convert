package com.practice.spring_java.domain.recruit.entity;

import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//채용 공고링크
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitBenefits extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_benefit_id")
    private long id;

    private String icon; //이모지 아이콘

    private String title;

    private String description;

    private boolean isActive;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    //업로드 파일
    @OneToMany(mappedBy = "recruitBenefits", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<UploadFile> uploadFile = new ArrayList<>();

    @Builder
    public RecruitBenefits(String icon, String title, String description, boolean isActive, LocalDate register_date,
                           boolean isDeleted, Recruit recruit, List<UploadFile> uploadFile) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.recruit = recruit;
        this.uploadFile = uploadFile;
    }
}