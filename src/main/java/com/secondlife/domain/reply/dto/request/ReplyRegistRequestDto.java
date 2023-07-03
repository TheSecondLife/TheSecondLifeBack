package com.secondlife.domain.reply.dto.request;

import com.secondlife.domain.comment.entity.Comment;
import com.secondlife.domain.reply.entity.Reply;
import com.secondlife.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyRegistRequestDto {

    private String content;

    private User user;

    private Comment comment;

    public void setUser(User user) {
        this.user = user;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Builder
    public Reply toEntity() {

        return Reply.builder()
                .content(this.content)
                .user(this.user)
                .comment(this.comment)
                .build();
    }
}
