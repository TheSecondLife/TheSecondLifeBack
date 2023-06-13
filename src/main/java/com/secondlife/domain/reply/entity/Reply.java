package com.secondlife.domain.reply.entity;


import com.secondlife.domain.comment.entity.Comment;
import com.secondlife.domain.global.BaseTimeEntity;
import com.secondlife.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Reply extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Column(length = 1000, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    public Reply(String content, Comment comment, User user) {
        this.content = content;
        this.comment = comment;
        this.user = user;
    }

    public void update(String content) {
        this.content = content;
    }

}