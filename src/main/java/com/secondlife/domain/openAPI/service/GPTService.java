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

        String inputMessage = gptCompletionChatRequestDto.getMessage();
        String defaultMessage = " 어디 병원에 가야해? " +
                "일반의, 내과, 신경과, 정신건강의학과, 외과, 정형외과, 신경외과, 심장혈관흉부외과, 성형외과, 마취통증의학과, 산부인과, " +
                "소아청소년과, 안과, 이비인후과, 피부과, 비뇨의학과, 영상의학과, 방사선종양학과, 병리과, 진단검사의학과, 결핵과, 재활의학과, " +
                "핵의학과, 가정의학과, 응급의학과, 직업환경의학과, 예방의학과, 보건, 보건기관치과, 보건기관한방, 치과, 구강악안면외과, 치과보철과, " +
                "치과교정과, 소아치과, 치주과, 치과보존과, 구강내과, 영상치의학과, 구강병리과, 예방치과, 치과소계, 통합치의학과, 한방내과, 한방부인과, " +
                "한방소아과, 한방신경정신과, 침구과, 한방재활의학과, 사상체질과, 한방응급과" +
                " 중에서 골라서 단어로만 말해줘.";

        gptCompletionChatRequestDto.setMessage(inputMessage + defaultMessage);

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
