package com.secondlife.domain.friend.entity;

import com.secondlife.domain.global.BaseTimeEntity;
import com.secondlife.domain.global.BooleanToYNConverter;

import com.secondlife.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Friend extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    @Column(nullable = false)
    @Convert(converter = BooleanToYNConverter.class)
    private boolean areWeFriend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_user_id")
    private User requestUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_user_id")
    private User responseUser;

    @Builder
    public Friend(Long id, boolean areWeFriend, User requestUser, User responseUser) {

        this.id = id;
        this.areWeFriend = areWeFriend;
        this.requestUser = requestUser;
        this.responseUser = responseUser;
    }

    public void acceptFriendRequest() {
        this.areWeFriend = true;
    }
}
