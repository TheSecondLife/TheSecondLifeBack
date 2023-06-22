package com.secondlife.domain.chat.api;

import com.secondlife.domain.chat.dto.request.TempChatDto;
import com.secondlife.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void sendMessage(TempChatDto chatDto, SimpMessageHeaderAccessor accessor) {
        System.out.println(chatDto);
        // 연결시 기존방인지 아닌지 확인

        chatRoomService.enterUser();
//        chatRoomService.mongoInsert(chatDto);
        simpMessagingTemplate.convertAndSend("/sub/chat/" + chatDto.getRoomId(), chatDto);
    }
}
