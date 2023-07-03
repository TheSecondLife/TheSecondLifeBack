package com.secondlife.domain.comment.api;

import com.secondlife.domain.comment.dto.request.CommentRegistRequestDto;
import com.secondlife.domain.comment.dto.request.CommentUpdateRequestDto;
import com.secondlife.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment/")
public class CommentController {

    private final CommentService commentService;

    // id로 Comment 조회
    @GetMapping("{id}")
    public ResponseEntity<?> findCommentById(@PathVariable Long id) {

        return ResponseEntity.ok()
                .body(commentService.findCommentById(id));
    }

    // 전체 Comment 조회
    @GetMapping("list")
    public ResponseEntity<?> findCommentList() {

        return ResponseEntity.ok()
                .body(commentService.findCommentList());
    }

    // 특정 post에 달린 Comment 조회
    @GetMapping("list/{postId}")
    public ResponseEntity<?> findCommentListByPostId(@PathVariable Long postId) {

        return ResponseEntity.ok()
                .body(commentService.findCommentListByPostId(postId));

    }

    // Comment 등록
    @PostMapping("regist/{userId}/{postId}")
    public ResponseEntity<?> registComment(@PathVariable Long userId, @PathVariable Long postId,
                                           @RequestBody CommentRegistRequestDto commentRegistRequestDto) {

        return ResponseEntity.ok()
                .body(commentService.registComment(userId, postId, commentRegistRequestDto));
    }

    // Comment 수정
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {

        return ResponseEntity.ok()
                .body(commentService.updateComment(id, commentUpdateRequestDto));
    }

    // Comment 삭제
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {

        return ResponseEntity.ok()
                .body(commentService.deleteComment(id));
    }
}
