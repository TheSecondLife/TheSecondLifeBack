package com.secondlife.domain.user.entity;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public enum Grade {
    씨앗, 떡잎, 줄기, 꽃, 열매
}

// 등급을 올릴 조건
// 게시글 삭제하면 등급내려감?
//    int score = len(postList) * 3 + len(commentList)
//    if (score > 10) {
//        grade = 1
//    } else if {
//
//    }
//    user.setGrade(grade)