package com.secondlife.domain.post.dto.response;

import com.secondlife.domain.post.entity.Category;
import com.secondlife.domain.post.entity.Post;
import com.secondlife.domain.user.entity.enums.Grade;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListResponseDto {

    // Post

    private Long id;

    private String title;

    private String content;

    private String img;

    private Category category;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


    // User

    private Long userId;

    private String userNickName;

    private String userProfileImg;

    private Grade userGrade;


    // Comment

    private long commentCnt;

    @Builder
    public PostListResponseDto(Post post) {

        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.img = post.getImg();
        this.category = post.getCategory();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();

        this.userId = post.getUser().getId();
        this.userNickName = post.getUser().getNickname();
        this.userProfileImg = post.getUser().getProfileImg();
        this.userGrade = post.getUser().getGrade();

        this.commentCnt = post.getCommentList().size();
    }
}
