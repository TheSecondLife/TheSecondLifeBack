package com.secondlife.domain.user.service;

import com.secondlife.domain.user.dto.request.UserEnterRequestDto;
import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 이메일 중복 검사(가입가능 : true)
    public boolean checkEmail(String email) {
        User checkUser = userRepository.findByEmail(email);
        return checkUser == null;
    }

    // 회원가입
    @Transactional
    public boolean regist(UserEnterRequestDto requestDto) throws NoSuchAlgorithmException {
        if (!checkEmail(requestDto.getEmail()))
            return false;
        String password = requestDto.getPassword();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        String hashPassword = String.format("%064x", new BigInteger(1, md.digest()));
        requestDto.hashing(hashPassword);
        userRepository.save(requestDto.toEntity());
        return true;
    }

}