package com.ssafy.study.domain.post.controller.dto;

import com.ssafy.study.domain.member.controller.dto.MemberResponse;
import com.ssafy.study.domain.post.entity.PostEntity;

public record PostResponse(Long id, String title, String content, MemberResponse memberResponse) {
    public static PostResponse from(PostEntity entity) {
        return new PostResponse(entity.getId(), entity.getTitle(), entity.getContent(), MemberResponse.from(entity.getAuthor()));
    }
}
