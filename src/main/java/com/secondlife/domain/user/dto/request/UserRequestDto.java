package com.secondlife.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserRequestDto {
    // 로그인, 비밀번호 변경
    private String email;
    private String password;

}
