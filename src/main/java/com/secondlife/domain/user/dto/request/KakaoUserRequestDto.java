package com.secondlife.domain.user.dto.request;

import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.entity.enums.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@ToString
public class KakaoUserRequestDto {

    private String email;
    private String name;
    private String profileImg;
    private String nickname;
    private int age;
    private final Grade grade = Grade.떡잎;

    public User toEntity() {
        return User.builder()
                .requestDto(this)
                .build();
    }
}
