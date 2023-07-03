package com.secondlife.domain.chat.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatItemResponseDto {

    private Long userId;
    private String profileImg;
    private String nickname;
    private String content;
    private String createdDate;

}
