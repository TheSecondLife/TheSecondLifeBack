package com.secondlife.domain.chat.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChatItemResponseDto {

//    private Object chatId;
    private Long userId;
    private String profileImg;
    private String nickname;
    private String content;
    private String createdDate;

}
