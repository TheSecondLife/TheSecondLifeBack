package com.secondlife.domain.post.dto.response;

import com.secondlife.domain.comment.dto.response.CommentListResponseDto;
import com.secondlife.domain.comment.entity.Comment;
import com.secondlife.domain.post.entity.Category;
import com.secondlife.domain.post.entity.Post;
import com.secondlife.domain.user.entity.Grade;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetailResponseDto {

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

    private String profileImg;

    private Grade grade;


    // Comment

    private List<CommentListResponseDto> commentList;

    @Builder
    public PostDetailResponseDto(Post post) {

        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.img = post.getImg();
        this.category = post.getCategory();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();

        this.userId = post.getUser().getId();
        this.userNickName = post.getUser().getNickname();
        this.profileImg = post.getUser().getProfileImg();
        this.grade = post.getUser().getGrade();

        List<CommentListResponseDto> commentListResponseDtoList = new ArrayList<>();

        for (Comment comment : post.getCommentList())
            commentListResponseDtoList.add(new CommentListResponseDto(comment));

        this.commentList = commentListResponseDtoList;
    }
}
