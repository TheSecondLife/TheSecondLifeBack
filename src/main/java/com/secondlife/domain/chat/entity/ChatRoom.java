package com.secondlife.domain.chat.entity;

import com.secondlife.domain.global.BaseTimeEntity;
import com.secondlife.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "chatroom")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatRoom extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    @Column(name = "room_id")
    private String roomId;

    @ManyToOne
    @JoinColumn(name = "user_a")
    private User userA;

    @ManyToOne
    @JoinColumn(name = "user_b")
    private User userB;

    @Builder
    public ChatRoom(String roomId, User userA, User userB) {
        this.roomId = roomId;
        this.userA = userA;
        this.userB = userB;
    }

}