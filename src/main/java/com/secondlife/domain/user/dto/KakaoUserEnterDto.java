package com.secondlife.domain.user.dto;

import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.entity.enums.Grade;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class KakaoUserEnterDto {

    private String email;
    private String name;
    private String profileImg;
    private final String password = "kakao";
    private String nickname;
    private int age;
    private final Grade grade = Grade.떡잎;

    @Builder
    public KakaoUserEnterDto(String email, String name, String profileImg, String nickname, int age) {
        this.email = email;
        this.name = name;
        this.profileImg = profileImg;
        this.nickname = nickname;
        this.age = age;
    }


    public User toEntity() {
        return User.builder()
                .requestDto(this)
                .build();
    }
}
