package com.secondlife.domain.user.api;

import com.secondlife.domain.user.dto.request.UserEnterRequestDto;
import com.secondlife.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // 이메일 중복 검사
    @PostMapping("/check")
    public boolean checkEmail(@RequestBody String email) {
        return userService.checkEmail(email);
    }

    // 회원가입
    @PostMapping("")
    public Boolean enter(@RequestBody UserEnterRequestDto requestDto) throws NoSuchAlgorithmException {
        System.out.println("requestDto = " + requestDto);
        return userService.regist(requestDto);
    }

    // 로그인


    // 정보수정


    // 탈퇴
}
