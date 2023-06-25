package com.secondlife.domain.friend.api;

import com.secondlife.domain.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend/")
public class FriendController {

    private final FriendService friendService;

    // 친구 관계 조회 - userId 두 개 위치 상관 X - 친구면 true, 아니면 false
    @GetMapping("read/{userIdA}/{userIdB}")
    public ResponseEntity<?> findFriendByTwoUserId(@PathVariable("userIdA") Long userIdA, @PathVariable("userIdB") Long userIdB) {

        return ResponseEntity.ok()
                .body(friendService.checkFriend(userIdA, userIdB));
    }

    // 친구 요청 조회 - 입력 위치 상관 O - 해당 친구 요청이 있으면 true, 없으면 false
    @GetMapping("read/request/{requestUserId}/{responseUserId}")
    public ResponseEntity<?> findFriendRequestByRequestUserIdAndResponseUserId
    (@PathVariable("requestUserId") Long requestUserId, @PathVariable("responseUserId") Long responseUserId) {

        return ResponseEntity.ok()
                .body(friendService.checkFriendRequest(requestUserId, responseUserId));
    }

    // 입력 받은 유저의 친구 리스트 조회 (단순 요청 말고 양방향으로 친구관계인 경우만)
    @GetMapping("read/list/{userId}")
    public ResponseEntity<?> getFriendListByUserId(@PathVariable("userId") Long userId) {

        return ResponseEntity.ok()
                .body(friendService.getFriendList(userId));
    }


    // 친구 요청 - 순서 상관 O
    @PostMapping("request/{requestUserId}/{responseUserId}")
    public ResponseEntity<?> requestFriend(@PathVariable("requestUserId") Long requestUserId, @PathVariable("responseUserId") Long responseUserId) {

        return ResponseEntity.ok()
                .body(friendService.requestFriend(requestUserId, responseUserId));
    }

    // 친구 요청 수락 - 순서 상관 O
    @PutMapping("accept/{myId}/{yourId}")
    public ResponseEntity<?> acceptFriendRequest(@PathVariable("myId") Long myId, @PathVariable("yourId") Long yourId) {

        return ResponseEntity.ok()
                .body(friendService.acceptFriendRequest(myId, yourId));
    }

    // 친구 요청 거절 - 순서 상관 O
    @DeleteMapping("refuse/{myId}/{yourId}")
    public ResponseEntity<?> refuseFriendRequest(@PathVariable("myId") Long myId, @PathVariable("yourId") Long yourId) {

        return ResponseEntity.ok()
                .body(friendService.refuseFriendRequest(myId, yourId));
    }

    // 친구 삭제 - 순서 상관 X
    @DeleteMapping("delete/{myId}/{yourId}")
    public ResponseEntity<?> deleteFriend(@PathVariable("myId") Long myId, @PathVariable("yourId") Long yourId) {

        return ResponseEntity.ok()
                .body(friendService.deleteFriend(myId, yourId));
    }

}
