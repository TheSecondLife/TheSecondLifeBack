package com.secondlife.domain.user.api;

import com.secondlife.domain.user.dto.request.KakaoCodeRequestDto;
import com.secondlife.domain.user.service.KakaoLoginService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kakao")
@CrossOrigin("*")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    @PostMapping("/code")
    public ResponseEntity<?> kakaoCallback(@RequestBody KakaoCodeRequestDto requestDto) {
        return kakaoLoginService.kakaoLogin(requestDto);
    }
}
