package com.secondlife.domain.post.entity;

import com.secondlife.domain.comment.entity.Comment;
import com.secondlife.domain.global.BaseTimeEntity;
import com.secondlife.domain.post.dto.request.PostUpdateRequestDto;
import com.secondlife.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 3000, nullable = false)
    private String content;

    @Column(length = 300, nullable = false)
    private String img;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Post(String title, String content, User user, String img, Category category) {

        this.title = title;
        this.content = content;
        this.user = user;
        this.img = img;
        this.category = category;
    }

    public Long update(PostUpdateRequestDto postUpdateRequestDto) {

        this.title = postUpdateRequestDto.getTitle();
        this.content = postUpdateRequestDto.getContent();
        this.img = postUpdateRequestDto.getImg();
        this.category = postUpdateRequestDto.getCategory();

        return this.id;
    }

    public void update(String title, String content, String img, Category category) {

        this.title = title;
        this.content = content;
        this.img = img;
        this.category = category;
    }
}
