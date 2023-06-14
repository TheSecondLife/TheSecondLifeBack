package com.secondlife.domain.user.dto.request;


import com.secondlife.domain.user.entity.Grade;
import com.secondlife.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserEnterRequestDto {

    private String email;
    private String name;
    private String password;
    private String nickname;
    private int age;

    private final Grade grade = Grade.떡잎;
    private final String profileImg = "https://fitsta-bucket.s3.ap-northeast-2.amazonaws.com/profile_default.jpg";

    public void hashing(String hashPassword) {
        this.password = hashPassword;
    }

    public User toEntity() {
        return User.builder()
                .requestDto(this)
                .build();
    }
}
