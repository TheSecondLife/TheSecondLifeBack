package com.secondlife.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UserInfoChangeRequestDto {
    Long userId;
    String name;
    String nickname;
}
