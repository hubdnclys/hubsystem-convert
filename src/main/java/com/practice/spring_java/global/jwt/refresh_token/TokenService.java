package com.practice.spring_java.global.jwt.refresh_token;

import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.employee.repository.EmployeeRepository;
import com.practice.spring_java.global.exception.ExpiredRefreshTokenException;
import com.practice.spring_java.global.exception.UserNotFoundException;
import com.practice.spring_java.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final EmployeeRepository userRepository;
    private final JwtProvider jwtProvider;

    public GetNewAccessTokenResponseDTO generateAccessToken(final RefreshToken refreshToken) {

        //redis의 refresh token의 유효기간이 지나지 않았는지
        RefreshToken refreshTokenCheck = refreshTokenRepository.findById(refreshToken.getRefreshToken())
                .orElseThrow(ExpiredRefreshTokenException::new);

        Employee user = userRepository.findById(refreshTokenCheck.getUserId())
                .orElseThrow(UserNotFoundException::new);

        return GetNewAccessTokenResponseDTO.fromEntity(jwtProvider.createToken(user));
    }
}