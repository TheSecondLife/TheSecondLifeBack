package com.secondlife.domain.reply.dto.response;

import com.secondlife.domain.reply.entity.Reply;
import com.secondlife.domain.user.entity.enums.Grade;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyResponseDto {

    // Reply

    private Long id;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


    // User

    private Long userId;

    private String userNickName;

    private String userProfileImg;

    private Grade userGrade;

    @Builder
    public ReplyResponseDto(Reply reply) {

        this.id = reply.getId();
        this.content = reply.getContent();
        this.createdDate = reply.getCreatedDate();
        this.modifiedDate = reply.getModifiedDate();

        this.userId = reply.getUser().getId();
        this.userNickName = reply.getUser().getNickname();
        this.userProfileImg = reply.getUser().getProfileImg();
        this.userGrade = reply.getUser().getGrade();
    }
}
