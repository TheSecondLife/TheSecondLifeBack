package com.secondlife.domain.comment.service;

import com.secondlife.domain.comment.dto.request.CommentRegistRequestDto;
import com.secondlife.domain.comment.dto.request.CommentUpdateRequestDto;
import com.secondlife.domain.comment.dto.response.CommentDetailResponseDto;
import com.secondlife.domain.comment.dto.response.CommentListResponseDto;
import com.secondlife.domain.comment.entity.Comment;
import com.secondlife.domain.comment.repository.CommentRepository;
import com.secondlife.domain.post.dto.request.PostRegistRequestDto;
import com.secondlife.domain.post.entity.Post;
import com.secondlife.domain.post.repository.PostRepository;
import com.secondlife.domain.user.entity.User;
import com.secondlife.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    // Id로 Comment 조회
    public CommentDetailResponseDto findCommentById(Long id) {

        Comment findComment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 comment가 존재하지 않습니다."));

        return new CommentDetailResponseDto(findComment);
    }

    // 전체 Comment 조회
    public List<CommentListResponseDto> findCommentList() {

        List<CommentListResponseDto> findCommentListResponseDtoList = new ArrayList<>();

        List<Comment> findCommentList = commentRepository.findAll();

        for (Comment comment : findCommentList)
            findCommentListResponseDtoList.add(new CommentListResponseDto(comment));

        return findCommentListResponseDtoList;
    }

    // 특정 Post에 달린 Comment 리스트 조회
    public List<CommentListResponseDto> findCommentListByPostId(Long postId) {

        List<CommentListResponseDto> findCommentListResponseDtoList = new ArrayList<>();

        List<Comment> findCommentList = commentRepository.findAllCommentListByPostId(postId);

        for (Comment comment : findCommentList)
            findCommentListResponseDtoList.add(new CommentListResponseDto(comment));

        return findCommentListResponseDtoList;
    }

    // Comment 등록
    @Transactional
    public Long registComment(Long userId, Long postId, CommentRegistRequestDto commentRegistRequestDto) {

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 user가 존재하지 않습니다."));

        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 post가 존재하지 않습니다."));

        commentRegistRequestDto.setUser(findUser);
        commentRegistRequestDto.setPost(findPost);

        Comment registComment = commentRegistRequestDto.toEntity();

        commentRepository.save(registComment);

        return registComment.getId();
    }
    
    // Comment 수정
    @Transactional
    public Long updateComment(Long id, CommentUpdateRequestDto commentUpdateRequestDto) {

        Comment findComment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 comment가 존재하지 않습니다."));

        return findComment.update(commentUpdateRequestDto);
    }
    
    // Comment 삭제
    @Transactional
    public Long deleteComment(Long id) {

        commentRepository.deleteById(id);

        return id;
    }
}
