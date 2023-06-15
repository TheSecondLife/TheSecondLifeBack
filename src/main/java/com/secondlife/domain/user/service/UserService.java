package com.secondlife.domain.user.service;

import com.secondlife.domain.user.dto.request.UserRequestDto;
import com.secondlife.domain.user.dto.request.UserEnterRequestDto;
import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

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
        userRepository.save(requestDto.toEntity());
        return true;
    }

    // 로그인
    public boolean login(UserRequestDto requestDto) {
        if (isAvailable(requestDto.getEmail()))
            return false;
        User user = userRepository.findByEmail(requestDto.getEmail()).get();
        return user.getPassword().equals(hashing(requestDto.getPassword()));
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
