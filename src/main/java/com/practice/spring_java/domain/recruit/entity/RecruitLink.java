package com.practice.spring_java.domain.recruit.entity;

import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//채용 공고링크
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitLink extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_link_id")
    private long id;

    private String siteName; //공고 사이트 ex 원티드 사람인..

    private String siteLink; //사이트 주소

    private int wantedCount; //지원자 수

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    @Builder
    private RecruitLink(String siteName, String siteLink, Recruit recruit) {
        this.siteName = siteName;
        this.siteLink = siteLink;
        this.wantedCount = 0;
        this.isDeleted = false;
        this.recruit = recruit;
    }
}