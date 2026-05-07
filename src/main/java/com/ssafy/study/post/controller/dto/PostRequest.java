package com.ssafy.study.post.controller.dto;

import com.ssafy.study.post.entity.PostEntity;

public class PostRequest {
    private String title,content,author;

    public PostRequest() {
    }

    public PostRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public PostEntity toEntity(){
        return new PostEntity(this.title,this.content,this.author);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
