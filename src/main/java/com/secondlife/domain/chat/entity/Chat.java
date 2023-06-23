package com.secondlife.domain.chat.entity;

import com.secondlife.domain.chat.dto.request.TempChatDto;
import com.secondlife.domain.global.BaseTimeEntity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Document(collection = "chat")
@NoArgsConstructor
@ToString
public class Chat {

    @Id
    private String id;
    private String roomId;
    private Long userId;
    private String nickname;
    private String profileImg;
    private String chat;
    private String time =  new Timestamp(System.currentTimeMillis()).toString();

    public Chat(TempChatDto dto) {
        this.roomId = dto.getRoomId();
        this.userId = dto.getUserId();
        this.nickname = dto.getNickname();
        this.profileImg = dto.getProfileImg();
        this.chat = dto.getChat();
    }
}
