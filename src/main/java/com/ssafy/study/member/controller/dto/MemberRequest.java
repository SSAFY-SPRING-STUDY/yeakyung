package com.ssafy.study.member.controller.dto;

import com.ssafy.study.member.entity.MemberEntity;

public record MemberRequest(String username, String password, String nickname) {

    public MemberEntity toEntity() {
        return MemberEntity.create(this.username, this.password, this.nickname);
    }
}
