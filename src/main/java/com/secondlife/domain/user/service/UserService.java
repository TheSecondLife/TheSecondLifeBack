package com.secondlife.domain.user.service;

import com.secondlife.domain.user.dto.request.UserRequestDto;
import com.secondlife.domain.user.dto.request.UserEnterRequestDto;
import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.repository.UserRepository;
import com.secondlife.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    private static final String SUCCESS = "succes";
    private static final String FAIL = "fail";

    // 이메일 중복 검사
    public boolean isExistEmail(String email) {
        return isAvailable(email);
    }

    // 회원가입
    @Transactional
    public boolean regist(UserEnterRequestDto requestDto) {
        if (!isAvailable(requestDto.getEmail()))
            return false;
        requestDto.hashingPassword(hashing(requestDto.getPassword()));
//        userRepository.save(requestDto.toEntity());
        return true;
    }

    // 로그인
    public ResponseEntity<?> login(UserRequestDto requestDto) {
        if (isAvailable(requestDto.getEmail()))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        User user = userRepository.findByEmail(requestDto.getEmail()).get();
        if (!user.getPassword().equals(hashing(requestDto.getPassword())))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        Map<String, Object> result = new HashMap<String, Object>();
        HttpStatus status;
        try {
            result.put("access-token", jwtUtil.createToken(user.getEmail(), user.getPassword()));
            result.put("message", SUCCESS);
            result.put("email", user.getEmail());
            result.put("name", user.getName());
            result.put("id", user.getId());
            result.put("age", user.getAge());
            result.put("nickname", user.getNickname());
            result.put("profileImg", user.getProfileImg());
            status = HttpStatus.OK;
        } catch (UnsupportedEncodingException e) {
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(result, status);

    }

    // 비밀번호 수정
    public void changePassword(UserRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail()).get();
        user.updatePassword(hashing(requestDto.getPassword()));
    }

    // 정보 수정(비밀번호x, 프로필사진x)


    // 프로필사진 변경


    // 비밀번호 암호화
    private String hashing(String password) {
        String hashPassword;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(password.getBytes());
        hashPassword = String.format("%064x", new BigInteger(1, md.digest()));
        return hashPassword;
    }

    // 존재하는 아이디 인지 검사
    private boolean isAvailable(String email) {
        Optional<User> checkUser = userRepository.findByEmail(email);
        return checkUser.isEmpty();
    }
}
