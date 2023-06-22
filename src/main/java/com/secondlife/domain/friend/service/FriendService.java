package com.secondlife.domain.friend.service;

import com.secondlife.domain.friend.dto.request.FriendRequestDto;
import com.secondlife.domain.friend.dto.response.FriendResponseDto;
import com.secondlife.domain.friend.entity.Friend;
import com.secondlife.domain.friend.repository.FriendRepository;
import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendService {

    private final FriendRepository friendRepository;

    private final UserRepository userRepository;

    // 친구 관계 조회(친구관계면 true, 아니면 false) - userId 두 개 위치 상관 X
    public boolean checkFriend(Long userIdA, Long userIdB) {

        return friendRepository.findFriendByRequestUserIdAndResponseUserId(userIdA, userIdB)
                .orElse(false);
    }

    // 친구 요청 조회(친구 요청이 있었다면 true, 없었다면 false) - 친구 관계 조회와 다름 - 입력 위치 상관 O)
    public boolean checkFriendRequest(Long requestUserId, Long responseUserId) {

        return friendRepository.findFriendRequestByRequestUserIdAndResponseUserId(requestUserId, responseUserId)
                .isPresent();
    }

    // 입력 받은 사람의 친구 리스트
    public List<FriendResponseDto> getFriendList(Long userId) {

        return friendRepository.findFriendListByUserId(userId);
    }

    // 친구 요청
    @Transactional
    public ResponseEntity<?> requestFriend(Long requestUserId, Long responseUserId) {

        // 만약 이미 친구 관계라면 - 요청 불가능
        if(checkFriend(requestUserId, responseUserId))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        // 만약 내가 그 전에 먼저 상대방에게 친구 요청을 했었다면 - 요청 불가능
        else if(checkFriendRequest(requestUserId, responseUserId))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        // 만약 상대방이 그 전에 먼저 나한테 친구 요청을 했었다면 - 친구관계 완성
        else if(checkFriendRequest(responseUserId, requestUserId)) {
            acceptFriendRequest(responseUserId, requestUserId)
//            return new ResponseEntity<Long>(friendRequest.getId(), HttpStatus.OK);
        }

        User requestUser = userRepository.findById(requestUserId)
                .orElseThrow(() -> new IllegalArgumentException("해당 User가 존재하지 않습니다."));

        User responseUser = userRepository.findById(responseUserId)
                .orElseThrow(() -> new IllegalArgumentException("해당 User가 존재하지 않습니다."));

        FriendRequestDto friendRequestDto = new FriendRequestDto(requestUser, responseUser);

        Friend friendRequest = FriendRequestDto.toEntity(friendRequestDto);

        friendRepository.save(friendRequest);

        return new ResponseEntity<Long>(friendRequest.getId(), HttpStatus.OK);
    }

    // 친구 요청 수락
    @Transactional
    public ResponseEntity<?> acceptFriendRequest(Long myId, Long yourId) {

        // 이미 친구 관계이거나, 상대방이 친구 요청을 한 기록이 없는 경우 - 수락 불가능
        if(checkFriend(myId, yourId) || !checkFriendRequest(yourId, myId))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        // Friend 테이블에 있었던 기존 친구 요청의 areWeFriend => true로 변경
        Friend friendRequest = friendRepository.findFriendRequestByRequestUserIdAndResponseUserId(yourId, myId).get();
        friendRequest.acceptFriendRequest();

        // 내가 상대방에게 요청했고 areWeFriend가 true인 column 삽입
        
    }
}
