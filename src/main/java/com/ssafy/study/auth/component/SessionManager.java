package com.ssafy.study.auth.component;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatusCode.valueOf(401),"해당 토큰을 가진 유저가 없습니다.");
        }
        System.out.println(sessionDB);
        return memberId;
    }

}
