package com.secondlife.domain.openAPI.entity;

import com.secondlife.domain.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gpt_answer")
public class GPTAnswer extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3000, nullable = false)
    private String answer;

    @Builder
    public GPTAnswer(Long id, String answer) {

        this.id = id;
        this.answer = answer;
    }

    public GPTAnswer(String answer) {

        this.answer = answer;
    }
}
