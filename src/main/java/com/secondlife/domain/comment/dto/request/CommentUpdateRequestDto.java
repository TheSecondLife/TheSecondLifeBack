package com.secondlife.domain.comment.dto.request;

import com.secondlife.domain.comment.entity.Comment;
import com.secondlife.domain.post.entity.Post;
import com.secondlife.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateRequestDto {

    private String content;

    private User user;

    private Post post;

    public Comment toEntity() {

        return Comment.builder()
                .content(this.content)
                .user(this.user)
                .post(this.post)
                .build();
    }
}
