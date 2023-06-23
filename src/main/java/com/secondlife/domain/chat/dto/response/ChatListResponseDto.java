package com.secondlife.domain.chat.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@ToString
@Getter
public class ChatListResponseDto {

    private String roomId;
    private String profileImg;
    private String nickname;
    private String lastChat;
    private Long otherId;
    private LocalDateTime lastChatTime;

}
