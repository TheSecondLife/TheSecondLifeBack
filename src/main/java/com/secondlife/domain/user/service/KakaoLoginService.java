package com.secondlife.domain.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.secondlife.domain.user.dto.KakaoUserEnterDto;
import com.secondlife.domain.user.dto.request.KakaoCodeRequestDto;
import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.repository.UserRepository;
import com.secondlife.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoLoginService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    private static final String SUCCESS = "succes";
    private static final String FAIL = "fail";

    @Transactional
    public ResponseEntity<?> kakaoLogin(KakaoCodeRequestDto requestDto){
        String access_token = getAccessToken(requestDto);
        HashMap<String, Object> userInfo = getUserInfo(access_token);
        HashMap<String, Object> JWT = getJWT(userInfo);
        return new ResponseEntity<HashMap<String, Object>>(JWT, HttpStatus.OK);
    }

    public HashMap<String, Object> getJWT(HashMap<String, Object> userInfo) {
        if (isAvailable(userInfo.get("email").toString())) {
            enterUser(userInfo);
        }
        // 이메일로 유저 정보 조회후 JWT발행
        Optional<User> checkUser = userRepository.findByEmail(userInfo.get("email").toString());
        User user = checkUser.get();
        // JWT발행
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            result.put("access-token", jwtUtil.createToken(user.getEmail(), user.getPassword()));
            result.put("message", SUCCESS);
            result.put("email", user.getEmail());
            result.put("name", user.getName());
            result.put("id", user.getId());
            result.put("age", user.getAge());
            result.put("nickname", user.getNickname());
            result.put("profileImg", user.getProfileImg());
        } catch (UnsupportedEncodingException e) {
            result.put("message", FAIL);
        }
        return result;

    }

    public void enterUser(HashMap<String, Object> userInfo) {
        KakaoUserEnterDto enterUser = KakaoUserEnterDto
                .builder()
                .email(userInfo.get("email").toString())
                .name(userInfo.get("nickname").toString())
                .profileImg(userInfo.get("profileImg").toString())
                .nickname(userInfo.get("nickname").toString())
                .age(50)
                .build();
        User user = enterUser.toEntity();
        userRepository.save(user);
    }




    public String getAccessToken(KakaoCodeRequestDto requestDto) {

        KEY key = new KEY();
        String code = requestDto.getCode();
        String redirect_uri = requestDto.getRedirect();

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", key.getClient());
        body.add("client_secret", key.getSecret());
        body.add("redirect_uri", redirect_uri);
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(responseBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonNode.get("access_token").asText();
    }

    public HashMap<String, Object> getUserInfo(String access_token) {

        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + access_token);

            int responseCode = conn.getResponseCode();

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            StringBuilder result = new StringBuilder();

            while((line = br.readLine())!=null) {
                result.append(line);
            }

            // Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = JsonParser.parseString(result.toString());

            String email = "";
            String profileImg = "";
            String nickname = "";

            boolean has_email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            if (has_email) {
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            } else {
                email = element.getAsJsonObject().get("id").getAsString();
            }

            nickname = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();

            boolean has_profileImg = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            if (has_profileImg) {
                profileImg = element.getAsJsonObject().get("properties").getAsJsonObject().get("profile_image").getAsString();
            } else {
                profileImg = "https://fitsta-bucket.s3.ap-northeast-2.amazonaws.com/profile_default.jpg";
            }

            userInfo.put("email", email);
            userInfo.put("nickname", nickname);
            userInfo.put("profileImg", profileImg);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    // 존재하는 아이디 인지 검사
    private boolean isAvailable(String email) {
        Optional<User> checkUser = userRepository.findByEmail(email);
        return checkUser.isEmpty();
    }
}