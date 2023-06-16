package com.secondlife.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class KakaoCodeRequestDto {

    private String code;
    private String redirect;

}
