package com.secondlife.domain.post.dto.request;

import com.secondlife.domain.post.entity.Category;
import com.secondlife.domain.post.entity.Post;
import com.secondlife.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostRegistRequestDto {

    private String title;

    private String content;

    private String img;

    private Category category;

    private User user;

    public void setInfo(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void setURL(String img) {
        this.img = img;
    }

    public void setUser(User user) {

        this.user = user;
    }

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
