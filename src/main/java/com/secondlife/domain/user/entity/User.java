package com.secondlife.domain.user.entity;

import com.secondlife.domain.comment.entity.Comment;
import com.secondlife.domain.global.BaseTimeEntity;
import com.secondlife.domain.post.entity.Post;
import com.secondlife.domain.user.dto.KakaoUserEnterDto;
import com.secondlife.domain.user.entity.enums.Grade;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profileImg;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Interest> interestList;

    @Builder
    public User(KakaoUserEnterDto requestDto) {
        this.email = requestDto.getEmail();
        this.name = requestDto.getName();
        this.age = requestDto.getAge();
        this.password = requestDto.getPassword();
        this.nickname = requestDto.getNickname();
        this.profileImg = requestDto.getProfileImg();
        this.grade = requestDto.getGrade();
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateUserInfo(String nickname) {
        this.nickname = nickname;
    }

    public void updateProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void updateGrade(Grade grade) {
        this.grade = grade;
    }
}
