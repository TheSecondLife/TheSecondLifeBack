package com.secondlife.domain.post.dto.request;

import com.secondlife.domain.post.entity.Category;
import com.secondlife.domain.post.entity.Post;
import com.secondlife.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateRequestDto {

    private String title;

    private String content;

    private String img;

    private Category category;

    private User user;

    public Post toEntity() {

        return Post.builder()
                .title(this.title)
                .content(this.content)
                .img(this.img)
                .category(this.category)
                .user(this.user)
                .build();
    }
}
