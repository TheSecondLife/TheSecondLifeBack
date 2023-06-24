package com.secondlife.domain.chat.api;

import com.secondlife.domain.chat.dto.request.TempChatDto;
import com.secondlife.domain.chat.dto.response.ChatItemResponseDto;
import com.secondlife.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class SocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void sendMessage(TempChatDto chatDto, SimpMessageHeaderAccessor accessor) {

        ChatItemResponseDto chatItemResponseDto = new ChatItemResponseDto();
        chatItemResponseDto.setContent(chatDto.getContent());
        chatItemResponseDto.setProfileImg(chatDto.getProfileImg());
        chatItemResponseDto.setNickname(chatDto.getNickname());
        LocalDateTime localDateTime = LocalDateTime.now();
        chatItemResponseDto.setCreatedDate(localDateTime.toString());
        chatItemResponseDto.setUserId(chatDto.getUserId());

        chatRoomService.mongoInsert(chatDto);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatDto.getRoomId(), chatItemResponseDto);
    }
}
