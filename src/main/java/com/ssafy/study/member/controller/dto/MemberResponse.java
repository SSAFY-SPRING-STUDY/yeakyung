package com.ssafy.study.member.controller.dto;

import com.ssafy.study.member.entity.MemberEntity;

public record MemberResponse(Long id,
                             String username, String nickname) {

    public static MemberResponse from(MemberEntity member) {
        return new MemberResponse(member.getId(), member.getUsername(), member.getNickname());
    }

}
