package com.ssafy.study.auth.service;

import com.ssafy.study.auth.component.SessionManager;
import com.ssafy.study.auth.controller.dto.LoginRequest;
import com.ssafy.study.auth.controller.dto.LoginResponse;
import com.ssafy.study.member.entity.MemberEntity;
import com.ssafy.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final SessionManager sessionManager;
    private final MemberRepository memberRepository;

    public LoginResponse login(LoginRequest request) {
        // 아이디(username) 검증
        MemberEntity member = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(401), "아이디 또는 비밀번호가 일치하지 않습니다."));
        // 비밀번호 검증
        if (!member.checkPassword(request.password())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        // 세션 생성
        String sessionKey = sessionManager.createSession(member.getId());
        return LoginResponse.from(sessionKey);
    }

    public void logout(String sessionKey) {
        sessionManager.removeSession(sessionKey);
    }

}
