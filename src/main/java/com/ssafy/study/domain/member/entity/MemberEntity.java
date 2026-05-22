package com.ssafy.study.domain.member.entity;

import lombok.Getter;

@Getter
public class MemberEntity {
    static long AUTO_INCREMENT = 1L;
    Long id;
    String username, password, nickname;

    private MemberEntity(Long id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public static MemberEntity create(String username, String password, String nickname) {
        return new MemberEntity(AUTO_INCREMENT++, username, password, nickname);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
