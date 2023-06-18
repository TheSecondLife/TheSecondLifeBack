package com.secondlife.domain.openAPI.service;

import com.secondlife.domain.openAPI.dto.request.GPTCompletionChatRequestDto;
import com.secondlife.domain.openAPI.dto.response.GPTCompletionChatResponseDto;
import com.secondlife.domain.openAPI.entity.GPTAnswer;
import com.secondlife.domain.openAPI.entity.GPTQuestion;
import com.secondlife.domain.openAPI.repository.GPTAnswerRepository;
import com.secondlife.domain.openAPI.repository.GptQuestionRepository;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GPTService {

    private final OpenAiService openAiService;

    private final GptQuestionRepository gptQuestionRepository;

    private final GPTAnswerRepository gptAnswerRepository;

    @Transactional
    public GPTCompletionChatResponseDto completionChat(GPTCompletionChatRequestDto gptCompletionChatRequestDto) {

        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(GPTCompletionChatRequestDto.of(gptCompletionChatRequestDto));

        GPTCompletionChatResponseDto response = GPTCompletionChatResponseDto.of(chatCompletion);

        List<String> messageList = response.getMessages().stream()
                .map(GPTCompletionChatResponseDto.Message::getMessage)
                .collect(Collectors.toList());

        GPTAnswer gptAnswer = saveAnswer(messageList);

        saveQuestion(gptCompletionChatRequestDto.getMessage(), gptAnswer);

        return response;
    }

    private void saveQuestion(String question, GPTAnswer answer) {

        GPTQuestion gptQuestion = new GPTQuestion(question, answer);

        gptQuestionRepository.save(gptQuestion);
    }

    private GPTAnswer saveAnswer(List<String> response) {

        String answer = response.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining());

        return gptAnswerRepository.save(new GPTAnswer(answer));
    }
}
