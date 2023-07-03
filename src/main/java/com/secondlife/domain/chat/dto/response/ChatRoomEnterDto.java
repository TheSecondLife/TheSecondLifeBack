package com.secondlife.domain.chat.dto.response;

import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter
public class ChatRoomEnterDto {

    private String roomId;
    private String talkUserName;
    private List<ChatItemResponseDto> chatList;

}
