package com.ssafy.study.auth.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // 유틸리티 클래스의 인스턴스 생성 막음
public class AuthTokenUtils {
    static final String PREFIX_BEARER = "Bearer ";

    public static boolean isValidBearerToken(String bearerToken) {
        return bearerToken.startsWith(PREFIX_BEARER);
    }

    public static String parseBearerToken(String bearerToken) {
        if (!isValidBearerToken(bearerToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효한 토큰이 아닙니다.");
        }
        return bearerToken.substring(PREFIX_BEARER.length());
    }
}
