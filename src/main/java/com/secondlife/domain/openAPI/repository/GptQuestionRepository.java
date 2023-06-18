package com.secondlife.domain.openAPI.repository;

import com.secondlife.domain.openAPI.entity.GPTQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GptQuestionRepository extends JpaRepository<GPTQuestion, Long> {
}
