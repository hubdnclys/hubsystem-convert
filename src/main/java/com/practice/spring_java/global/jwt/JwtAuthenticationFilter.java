package com.practice.spring_java.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.spring_java.domain.employee.dto.request.LoginRequestDTO;
import com.practice.spring_java.domain.employee.dto.response.LoginResponseDTO;
import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.employee.repository.EmployeeRepository;
import com.practice.spring_java.global.jwt.refresh_token.RefreshTokenRepository;
import com.practice.spring_java.global.security.PrincipalDetails;
import com.practice.spring_java.global.util.ResponseDTO;
import com.practice.spring_java.global.util.SendJsonResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JWT를 이용한 로그인 인증 (Authentication) 코드
 */
@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtProvider jwtProvider;
    EmployeeRepository employeeRepository;
    RefreshTokenRepository refreshTokenRepository;

    public JwtAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtProvider jwtProvider,
            EmployeeRepository employeeRepository,
            RefreshTokenRepository refreshTokenRepository
    ) {
        super.setAuthenticationManager(authenticationManager);
        this.jwtProvider = jwtProvider;
        this.employeeRepository = employeeRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    /**
     * 로그인 인증 시도
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            // 요청된 JSON 데이터를 객체로 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            LoginRequestDTO loginRequest = objectMapper.readValue(request.getInputStream(),
                    LoginRequestDTO.class);

            // 로그인할 때 입력한 email과 password를 가지고 authenticationToken를 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.email(),
                    loginRequest.password(),
                    new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_USER")))
            );

            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException {
        Employee user = ((PrincipalDetails) authResult.getPrincipal()).getUser();
        String token = jwtProvider.createToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user, refreshTokenRepository);

        LoginResponseDTO loginResponseDto = LoginResponseDTO.fromEntity(user, token, refreshToken);
        ResponseDTO<LoginResponseDTO> loginResponse = ResponseDTO.okWithData(loginResponseDto);

        SendJsonResponse.sendJsonResponse(response, loginResponse, HttpStatus.OK);
    }

    /**
     * 인증실패
     */
    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        String authenticationErrorMessage = getAuthenticationErrorMessage(exception);

        ResponseDTO<Void> errorResponse = ResponseDTO.errorWithMessage(HttpStatus.BAD_REQUEST,
                authenticationErrorMessage);

        SendJsonResponse.sendJsonResponse(response, errorResponse, HttpStatus.BAD_REQUEST);
    }

    private String getAuthenticationErrorMessage(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException) {
            return "이메일 또는 비밀번호 에러";
        } else if (exception instanceof UsernameNotFoundException) {
            return "존재하지 않는 유저";
        } else {
            return "인증 실패";
        }
    }
}