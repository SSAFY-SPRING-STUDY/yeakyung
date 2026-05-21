package com.ssafy.study.member.controller;

import com.ssafy.study.auth.component.SessionManager;
import com.ssafy.study.member.controller.dto.MemberRequest;
import com.ssafy.study.member.controller.dto.MemberResponse;
import com.ssafy.study.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.ssafy.study.auth.util.AuthTokenUtils.parseBearerToken;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final SessionManager sessionManager;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse join(@RequestBody MemberRequest request) {
        return memberService.join(request);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse getMyInfo(@RequestHeader("Authorization") String bearerToken) {
        String token = parseBearerToken(bearerToken);
        Long id = sessionManager.getMemberId(token);
        return memberService.getMemberInfo(id);
    }
}
