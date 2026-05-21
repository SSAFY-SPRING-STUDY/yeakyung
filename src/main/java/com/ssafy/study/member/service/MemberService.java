package com.ssafy.study.member.service;

import com.ssafy.study.member.controller.dto.MemberRequest;
import com.ssafy.study.member.controller.dto.MemberResponse;
import com.ssafy.study.member.entity.MemberEntity;
import com.ssafy.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse join(MemberRequest request) {
        MemberEntity member = memberRepository.save(request.toEntity());
        return MemberResponse.from(member);
    }

    public MemberResponse getMemberInfo(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
        return MemberResponse.from(member);
    }

}
