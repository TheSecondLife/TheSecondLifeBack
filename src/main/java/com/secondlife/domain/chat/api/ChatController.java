package com.secondlife.domain.chat.api;


import com.secondlife.domain.chat.dto.request.ChatRoomEnterRequrstDto;
import com.secondlife.domain.chat.dto.response.ChatListResponseDto;
import com.secondlife.domain.chat.dto.response.ChatRoomEnterDto;
import com.secondlife.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getChatList(@PathVariable("id") Long userId) {
        List<ChatListResponseDto> result = chatRoomService.getUserChatList(userId);
        return new ResponseEntity<List<ChatListResponseDto>>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> enterChatRoom(@RequestBody ChatRoomEnterRequrstDto requrstDto) {
        if (requrstDto.getUserA() == null || requrstDto.getUserB() == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        if (Objects.equals(requrstDto.getUserA(), requrstDto.getUserB())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        ChatRoomEnterDto result = chatRoomService.enterUser(requrstDto);
        return new ResponseEntity<ChatRoomEnterDto>(result, HttpStatus.OK);
    }
}
