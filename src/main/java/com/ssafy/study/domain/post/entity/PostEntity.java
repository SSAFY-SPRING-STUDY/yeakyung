package com.ssafy.study.domain.post.entity;

import com.ssafy.study.domain.member.entity.MemberEntity;
import lombok.Getter;

@Getter
public class PostEntity {
    static long AUTO_INCREMENT_ID = 1L;
    private Long id;
    private String title;
    private String content;
    private MemberEntity author;

    public PostEntity(String title, String content, MemberEntity author) {
        this.author = author;
        this.content = content;
        this.title = title;
        this.id = AUTO_INCREMENT_ID++;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
