package com.secondlife.domain.openAPI.api;

import com.secondlife.domain.openAPI.dto.request.GPTCompletionChatRequestDto;
import com.secondlife.domain.openAPI.dto.response.GPTCompletionChatResponseDto;
import com.secondlife.domain.openAPI.service.GPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gpt/")
public class GPTController {

    private final GPTService gptService;

    @PostMapping("chat")
    public GPTCompletionChatResponseDto completionChat(final @RequestBody GPTCompletionChatRequestDto gptCompletionChatRequestDto) {

        return gptService.completionChat(gptCompletionChatRequestDto);
    }
}
