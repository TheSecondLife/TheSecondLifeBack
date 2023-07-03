package com.secondlife.domain.openAPI.dto.request;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GPTCompletionChatRequestDto {

    private String model;

    private String role;

    private String message;

    private Integer maxTokens;

    public void setMessage(String message) {
        this.message = message;
    }

    public static ChatCompletionRequest of(GPTCompletionChatRequestDto gptCompletionChatRequestDto) {

        return ChatCompletionRequest.builder()
                .model(gptCompletionChatRequestDto.getModel())
                .messages(convertChatMessage(gptCompletionChatRequestDto))
                .maxTokens(gptCompletionChatRequestDto.getMaxTokens())
                .build();
    }

    private static List<ChatMessage> convertChatMessage(GPTCompletionChatRequestDto gptCompletionChatRequestDto) {

        return List.of(new ChatMessage(gptCompletionChatRequestDto.getRole(), gptCompletionChatRequestDto.getMessage()));
    }
}
