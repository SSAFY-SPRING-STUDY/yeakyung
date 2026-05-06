package com.ssafy.study.post.controller.dto;

import com.ssafy.study.post.entity.PostEntity;

public record PostResponse(Long id, String title, String content, String author) {
    public static PostResponse from(PostEntity entity){
        return new PostResponse(entity.getId(),entity.getTitle(),entity.getContent(),entity.getAuthor());
    }
}
