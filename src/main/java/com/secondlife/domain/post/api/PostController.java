package com.secondlife.domain.post.api;

import com.secondlife.domain.post.dto.request.PostRegistRequestDto;
import com.secondlife.domain.post.dto.request.PostUpdateRequestDto;
import com.secondlife.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/")
public class PostController {

    private final PostService postService;

    // id로 Post 조회
    @GetMapping("{id}")
    public ResponseEntity<?> findPostById(@PathVariable("id") Long id) {

        return ResponseEntity.ok()
                .body(postService.findPostById(id));
    }

    // 전체 post 조회
    @GetMapping("list")
    public ResponseEntity<?> findPostList() {

        return ResponseEntity.ok()
                .body(postService.findPostList());
    }

    // post 등록
    @PostMapping("regist/{userId}")
    public ResponseEntity<?> registPost(@PathVariable("userId") Long userId, @RequestBody PostRegistRequestDto postRegistRequestDto) {

        Long registId = postService.registPost(userId, postRegistRequestDto);

        return ResponseEntity.ok()
                .body(registId);
    }

    // post 수정
    @PutMapping("update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto) {

        Long updateId = postService.updatePost(id, postUpdateRequestDto);

        return ResponseEntity.ok()
                .body(updateId);
    }

    // post 삭제
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {

        Long deleteId = postService.deletePost(id);

        return ResponseEntity.ok()
                .body(deleteId);
    }
}
