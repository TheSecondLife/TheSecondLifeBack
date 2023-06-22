package com.secondlife.domain.chat.entity;

import com.secondlife.domain.chat.dto.request.TempChatDto;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "chat")
@NoArgsConstructor
public class Chat {

    @Id
    private String id;
    private String roomId;
    private Long userId;
    private String nickname;
    private String profileImg;
    private String chat;

    public Chat(TempChatDto dto) {
        this.roomId = dto.getRoomId();
        this.userId = dto.getUserId();
        this.nickname = dto.getNickname();
        this.profileImg = dto.getProfileImg();
        this.chat = dto.getChat();
    }
}
