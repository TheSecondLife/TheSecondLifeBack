package com.secondlife.config;

// Chat GPT 라이브러리 사용 전에 토큰 주입


import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Slf4j
@Configuration
public class ChatGPTConfig {

    @Value("${GPT-TOKEN}")
    private String token;

    // token을 활용한 OpenAiService 생성
    @Bean
    public OpenAiService openAiService() {

        return new OpenAiService(token, Duration.ofSeconds(60));
    }
}
