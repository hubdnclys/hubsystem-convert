package com.practice.spring_java.domain.recruit.repository;

import com.practice.spring_java.domain.recruit.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository
        extends JpaRepository<UploadFile, Long> {

}
