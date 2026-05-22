package com.ssafy.study.domain.auth.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // 유틸리티 클래스의 인스턴스 생성 막음
public class AuthTokenUtils {
    static final String PREFIX_BEARER = "Bearer ";

    public static boolean isValidBearerToken(String bearerToken) {
        return bearerToken == null || !bearerToken.startsWith(PREFIX_BEARER);
    }

    public static String parseBearerToken(String bearerToken) {

        return bearerToken.substring(PREFIX_BEARER.length());
    }
}
