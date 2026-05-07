package com.ssafy.study.post.entity;

import lombok.Getter;

@Getter
public class PostEntity {
    private Long id;
    private String title;
    private String content;
    private String author;

    static long AUTO_INCREMENT_ID = 1L;

    public PostEntity(String title, String content, String author) {
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
