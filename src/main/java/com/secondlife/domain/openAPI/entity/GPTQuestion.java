package com.secondlife.domain.openAPI.entity;

import com.secondlife.domain.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gpt_question")
public class GPTQuestion extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3000, nullable = false)
    private String question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private GPTAnswer answer;


    public GPTQuestion(String question, GPTAnswer answer) {

        this.question = question;
        this.answer = answer;
    }
}
