package com.secondlife.domain.user.api;

import com.secondlife.domain.user.dto.request.UserInfoChangeRequestDto;
import com.secondlife.domain.user.dto.request.UserRequestDto;
import com.secondlife.domain.user.dto.request.UserEnterRequestDto;
import com.secondlife.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    // 이메일 중복 검사
    @PostMapping("/check")
    public boolean checkEmail(@RequestBody UserRequestDto requestDto) {
        return userService.isExistEmail(requestDto.getEmail());
    }

    // 회원가입
    @PostMapping("/enter")
    public boolean enter(@RequestBody UserEnterRequestDto requestDto) {
        return userService.regist(requestDto);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto requestDto) {
        return userService.login(requestDto);
    }

    // 비밀번호 수정
    @PostMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody UserRequestDto requestDto) {
        userService.changePassword(requestDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 정보수정
    @PostMapping("/info")
    public ResponseEntity<?> changeInfo(@RequestBody UserInfoChangeRequestDto requestDto) {
        userService.changeUserInfo(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 프로필 사진 변경
    @PostMapping("/profile")
    public ResponseEntity<?> changeProfileImg(@RequestParam("image") MultipartFile multipartFile, @RequestParam("id") Long id) throws IOException {
        String profileURL = userService.changeUserProfileImg(multipartFile, id);
        return new ResponseEntity<String>(profileURL, HttpStatus.OK);
    }
}
