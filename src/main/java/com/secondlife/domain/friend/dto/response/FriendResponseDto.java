package com.secondlife.domain.friend.dto.response;

import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.entity.enums.Grade;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendResponseDto {

    private Long friendUserId;

    private String friendName;

    private String friendNickname;

    private String friendProfileImg;

    private Grade friendGrade;

    @Builder
    public FriendResponseDto(User friend) {

        this.friendUserId = friend.getId();
        this.friendName = friend.getNickname();
        this.friendNickname = friend.getNickname();
        this.friendProfileImg = friend.getProfileImg();
        this.friendGrade = friend.getGrade();
    }
}
