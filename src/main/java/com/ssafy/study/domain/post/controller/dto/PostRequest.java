package com.ssafy.study.domain.post.controller.dto;

import com.ssafy.study.domain.member.entity.MemberEntity;
import com.ssafy.study.domain.post.entity.PostEntity;

public class PostRequest {
    private String title, content;

    public PostRequest() {
    }

    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostEntity toEntity(MemberEntity author) {
        return new PostEntity(this.title, this.content, author);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
