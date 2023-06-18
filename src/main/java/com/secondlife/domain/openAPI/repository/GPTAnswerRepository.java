package com.secondlife.domain.openAPI.repository;

import com.secondlife.domain.openAPI.entity.GPTAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GPTAnswerRepository extends JpaRepository<GPTAnswer, Long> {
}
