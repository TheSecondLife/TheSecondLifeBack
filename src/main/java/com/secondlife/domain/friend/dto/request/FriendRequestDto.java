package com.secondlife.domain.friend.dto.request;

import com.secondlife.domain.friend.entity.Friend;
import com.secondlife.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendRequestDto {

    private User requestUser;

    private User responseUser;

    @Builder
    public FriendRequestDto(User requestUser, User responseUser) {

        this.requestUser = requestUser;
        this.responseUser = responseUser;
    }

    public static Friend toEntity(FriendRequestDto friendRequestDto) {

        return Friend.builder()
                .areWeFriend(false)
                .requestUser(friendRequestDto.getRequestUser())
                .responseUser(friendRequestDto.getResponseUser())
                .build();
    }
}
