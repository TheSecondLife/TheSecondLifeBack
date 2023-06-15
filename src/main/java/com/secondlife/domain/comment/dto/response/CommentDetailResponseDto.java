package com.secondlife.domain.comment.dto.response;

import com.secondlife.domain.comment.entity.Comment;
import com.secondlife.domain.reply.dto.response.ReplyResponseDto;
import com.secondlife.domain.reply.entity.Reply;
import com.secondlife.domain.user.entity.enums.Grade;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDetailResponseDto {

    // Comment

    private Long id;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


    // User

    private Long userId;

    private String userNickName;

    private String userProfileImg;

    private Grade userGrade;


    // reply

    private List<ReplyResponseDto> replyList;

    @Builder
    public CommentDetailResponseDto(Comment comment) {

        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();

        this.userId = comment.getUser().getId();
        this.userNickName = comment.getUser().getNickname();
        this.userProfileImg = comment.getUser().getProfileImg();
        this.userGrade = comment.getUser().getGrade();

        List<ReplyResponseDto> replyResponseDtoList = new ArrayList<>();

        for (Reply reply : comment.getReplyList())
            replyResponseDtoList.add(new ReplyResponseDto(reply));

        this.replyList = replyResponseDtoList;
    }
}
