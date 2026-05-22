package com.ssafy.study.domain.auth.component;

import com.ssafy.study.global.exception.CustomException;
import com.ssafy.study.global.exception.error.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    private final Map<String, Long> sessionDB = new ConcurrentHashMap<>();

    public String createSession(Long memberId) {
        String sessionKey = UUID.randomUUID().toString();
        sessionDB.put(sessionKey, memberId);
        return sessionKey;
    }

    public void removeSession(String sessionKey) {
        sessionDB.remove(sessionKey);
    }

    public Long getMemberId(String sessionKey) {
        Long memberId = sessionDB.get(sessionKey);
        if (memberId == null) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }
        return memberId;
    }

}
