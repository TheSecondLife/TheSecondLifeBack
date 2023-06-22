package com.secondlife.domain.chat.service;

import com.secondlife.domain.chat.dto.request.ChatRoomEnterRequrstDto;
import com.secondlife.domain.chat.dto.request.TempChatDto;
import com.secondlife.domain.chat.dto.response.ChatListResponseDto;
import com.secondlife.domain.chat.dto.response.ChatRoomEnterDto;
import com.secondlife.domain.chat.entity.Chat;
import com.secondlife.domain.chat.repository.ChatRoomRepository;
import com.secondlife.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final MongoTemplate mongoTemplate;

    private final ChatRoomRepository chatRoomRepository;

    private final UserRepository userRepository;

    // 기존유저 처음 유저 구분
    public ChatRoomEnterDto enterUser(ChatRoomEnterRequrstDto requrstDto) {

        ChatRoomEnterDto result = new ChatRoomEnterDto();

        Long a = requrstDto.getUserA();
        Long b = requrstDto.getUserB();

        // 기존에 있는 채팅방이면 채팅 데이터 불러와야함
        int roomCount = chatRoomRepository.isExist(
                userRepository.findUserById(a),
                userRepository.findUserById(b));
        // 방 새로 생성
        if (roomCount == 0) {


        // mongodb에서 값 불러오기
        } else {

        }
        System.out.println(roomCount);
        // 아니면 새로 방을 만든다.
        return result;
    }

    public List<ChatListResponseDto> getUserChatList(Long userId) {
        List<ChatListResponseDto> result = new ArrayList<>();


        return result;
    }

    public void mongoInsert(TempChatDto dto) {
        Chat chat = new Chat(dto);
        mongoTemplate.insert(chat);
    }
}