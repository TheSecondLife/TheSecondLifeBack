package com.secondlife.domain.reply.api;

import com.secondlife.domain.reply.dto.request.ReplyRegistRequestDto;
import com.secondlife.domain.reply.dto.request.ReplyUpdateRequestDto;
import com.secondlife.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply/")
public class ReplyController {

    private final ReplyService replyService;

    // id로 Reply 조회
    @GetMapping("{id}")
    public ResponseEntity<?> findReplyById(@PathVariable Long id) {

        return ResponseEntity.ok()
                .body(replyService.findReplyById(id));
    }

    // 전체 Reply 조회
    @GetMapping("list")
    public ResponseEntity<?> findReplyList() {

        return ResponseEntity.ok()
                .body(replyService.findReplyList());
    }

    // 특정 Comment에 달린 Reply 조회
    @GetMapping("list/{commentId}")
    public ResponseEntity<?> findReplyListByCommentId(@PathVariable Long commentId) {

        return ResponseEntity.ok()
                .body(replyService.findReplyListByCommentId(commentId));
    }

    // Reply 등록
    @PostMapping("regist/{userId}/{commentId}")
    public ResponseEntity<?> registReply(@PathVariable Long userId, @PathVariable Long commentId,
                                         @RequestBody ReplyRegistRequestDto replyRegistRequestDto) {

        return ResponseEntity.ok()
                .body(replyService.registReply(userId, commentId, replyRegistRequestDto));
    }

    // Reply 수정
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateReply(@PathVariable Long id, @RequestBody ReplyUpdateRequestDto replyUpdateRequestDto) {

        return ResponseEntity.ok()
                .body(replyService.updateReply(id, replyUpdateRequestDto));
    }

    // Reply 삭제
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteReply(@PathVariable Long id) {

        return ResponseEntity.ok()
                .body(replyService.deleteReply(id));
    }
}
