package com.secondlife.domain.chat.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TempChatDto {

    private String roomId;
    private Long userId;
    private String nickname;
    private String profileImg;
    private String chat;

}
