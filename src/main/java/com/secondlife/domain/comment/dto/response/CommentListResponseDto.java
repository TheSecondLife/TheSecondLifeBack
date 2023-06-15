package com.secondlife.domain.comment.dto.response;

import com.secondlife.domain.comment.entity.Comment;
import com.secondlife.domain.user.entity.Grade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListResponseDto {

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

    public CommentListResponseDto(Comment comment) {

        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();

        this.userId = comment.getUser().getId();
        this.userNickName = comment.getUser().getNickname();
        this.userProfileImg = comment.getUser().getProfileImg();
        this.userGrade = comment.getUser().getGrade();
    }
}
