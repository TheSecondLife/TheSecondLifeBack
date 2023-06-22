package com.secondlife.domain.chat.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ChatRoomEnterDto {

    private String roomId;
    private List<ChatItemResponseDto> chatList;

}
