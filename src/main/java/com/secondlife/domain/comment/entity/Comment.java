package com.secondlife.domain.comment.entity;


import com.secondlife.domain.comment.dto.request.CommentUpdateRequestDto;
import com.secondlife.domain.global.BaseTimeEntity;
import com.secondlife.domain.post.entity.Post;
import com.secondlife.domain.reply.entity.Reply;
import com.secondlife.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(length = 1000, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @Builder
    public Comment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        this.user = user;
    }

    public Long update(CommentUpdateRequestDto commentUpdateRequestDto) {

        this.content = commentUpdateRequestDto.getContent();

        return this.id;
    }

    public void update(String content) {
        this.content = content;
    }

}