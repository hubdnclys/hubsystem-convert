package com.practice.spring_java.global.jwt.refresh_token;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    @Override
    Optional<RefreshToken> findById(String token);
}