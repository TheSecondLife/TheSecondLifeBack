package com.secondlife.domain.chat.service;

import com.secondlife.domain.chat.dto.request.ChatRoomEnterRequrstDto;
import com.secondlife.domain.chat.dto.request.TempChatDto;
import com.secondlife.domain.chat.dto.response.ChatItemResponseDto;
import com.secondlife.domain.chat.dto.response.ChatListResponseDto;
import com.secondlife.domain.chat.dto.response.ChatRoomEnterDto;
import com.secondlife.domain.chat.entity.Chat;
import com.secondlife.domain.chat.entity.ChatRoom;
import com.secondlife.domain.chat.repository.ChatRepository;
import com.secondlife.domain.chat.repository.ChatRoomRepository;
import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ChatRoomService {

//    private final MongoTemplate mongoTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    // 기존유저 처음 유저 구분
    @Transactional
    public ChatRoomEnterDto enterUser(ChatRoomEnterRequrstDto requrstDto) {

        ChatRoomEnterDto result = new ChatRoomEnterDto();

        Long a = requrstDto.getUserA();
        Long b = requrstDto.getUserB();
        User userA = userRepository.findUserById(a);
        User userB = userRepository.findUserById(b);

        // 기존에 있는 채팅방이면 채팅 데이터 불러와야함
        int roomCount = chatRoomRepository.isExist(userA, userB);
//        System.out.println(roomCount);
        // 방 새로 생성
        if (roomCount == 0) {
            String roodId = UUID.randomUUID().toString();
            ChatRoom chatRoom1 = ChatRoom.builder()
                    .roomId(roodId)
                    .userA(userA)
                    .userB(userB)
                    .lastChat("대화를 시작해 보세요")
                    .build();
            ChatRoom chatRoom2 = ChatRoom.builder()
                    .roomId(roodId)
                    .userA(userB)
                    .userB(userA)
                    .lastChat("대화를 시작해 보세요")
                    .build();
            chatRoomRepository.save(chatRoom1);
            chatRoomRepository.save(chatRoom2);
//            System.out.println("방새로 생성");
            result.setTalkUserName(userB.getNickname());
            result.setRoomId(roodId);
            return result;
        }
        // mongodb에서 값 불러오기
//        System.out.println("방있음 데이터 불러오기");
        ChatRoom chatRoom = chatRoomRepository.getRoomId(userA, userB);
        List<Chat> chatList = chatRepository.findByRoomId(chatRoom.getRoomId());
        List<ChatItemResponseDto> chatItemResponseDtoArrayList= new ArrayList<>();
        for (Chat c : chatList) {
            ChatItemResponseDto temp = new ChatItemResponseDto();

//            temp.setChatId(c.getId());
            temp.setUserId(c.getUserId());
            temp.setProfileImg(c.getProfileImg());
            temp.setNickname(c.getNickname());
            temp.setContent(c.getChat());
            temp.setCreatedDate(c.getTime());
//            System.out.println(temp);
            chatItemResponseDtoArrayList.add(temp);

        }
        ChatRoomEnterDto dto = new ChatRoomEnterDto();
        dto.setChatList(chatItemResponseDtoArrayList);
        dto.setRoomId(chatRoom.getRoomId());
        dto.setTalkUserName(userB.getNickname());

        return dto;
    }

    public List<ChatListResponseDto> getUserChatList(Long userId) {

        List<ChatListResponseDto> chatList = new ArrayList<>();
        User userA = userRepository.findUserById(userId);
//        System.out.println(userA);
        List<ChatRoom> chatRoomList = chatRoomRepository.findByUserA(userA);
//        System.out.println(chatRoomList.size());
        for (ChatRoom cr : chatRoomList) {
            ChatListResponseDto dto = new ChatListResponseDto();
            dto.setLastChat(cr.getLastChat());
            dto.setProfileImg(cr.getUserB().getProfileImg());
            dto.setRoomId(cr.getRoomId());
            dto.setOtherId(cr.getUserB().getId());
            dto.setNickname(cr.getUserB().getNickname());
            dto.setLastChatTime(cr.getModifiedDate());
            chatList.add(dto);
//            System.out.println(dto);
        }
        return chatList;
    }

    @Transactional
    public void mongoInsert(TempChatDto dto) {
        Chat chat = new Chat(dto);
        List<ChatRoom> chatRoom = chatRoomRepository.findByRoomId(dto.getRoomId());
        for (ChatRoom cr : chatRoom) {
            cr.updateLastChat(dto.getContent());
        }
        chatRepository.save(chat);
    }
}