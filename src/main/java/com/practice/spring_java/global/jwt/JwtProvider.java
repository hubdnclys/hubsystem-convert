package com.practice.spring_java.global.jwt;

import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.global.jwt.refresh_token.RefreshToken;
import com.practice.spring_java.global.jwt.refresh_token.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    /**
     * 토큰에서 유저 정보를 추출하는 코드
     */
    public String getEmail(String token) {
        // jwtToken에서 email을 찾습니다.
        return Jwts.parserBuilder()
                .setSigningKeyResolver(SigningKeyResolver.instance)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * user 로 토큰 생성 HEADER : alg, kid PAYLOAD : sub, iat, exp SIGNATURE : JwtKey.getRandomKey로 구한
     * Secret Key로 HS512 해시
     *
     * @param user 유저
     * @return jwt token
     */
    public String createToken(Employee user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail()); // subject
        Date now = new Date(); // 현재 시간
        Pair<String, Key> key = JwtKey.getRandomKey();
        // JWT Token 생성
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(
                        new Date(now.getTime() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME)) // 토큰 만료 시간 설정
                .setHeaderParam(JwsHeader.KEY_ID, key.getFirst()) // kid
                .signWith(key.getSecond()) // signature
                .compact();
    }

    public String createRefreshToken(Employee user, RefreshTokenRepository refreshTokenRepository) {
        String refreshToken = UUID.randomUUID().toString();
        RefreshToken redisRefreshToken = RefreshToken.builder().
                refreshToken(refreshToken).
                userId(user.getId()).
                build();

        refreshTokenRepository.save(redisRefreshToken);

        return refreshToken;
    }
}
