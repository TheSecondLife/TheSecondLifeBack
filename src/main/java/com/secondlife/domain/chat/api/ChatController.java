package com.secondlife.domain.chat.api;


import com.secondlife.domain.chat.dto.request.ChatRoomEnterRequrstDto;
import com.secondlife.domain.chat.dto.response.ChatListResponseDto;
import com.secondlife.domain.chat.dto.response.ChatRoomEnterDto;
import com.secondlife.domain.chat.service.ChatRoomService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/{id}")
    public List<ChatListResponseDto> getChatList(@PathVariable("id") Long userId) {
        return chatRoomService.getUserChatList(userId);
    }

    @PostMapping("/")
    public ResponseEntity<?> enterChatRoom(@RequestBody ChatRoomEnterRequrstDto requrstDto) {
        ChatRoomEnterDto result = chatRoomService.enterUser(requrstDto);
        return new ResponseEntity<ChatRoomEnterDto>(result, HttpStatus.OK);
    }
}
