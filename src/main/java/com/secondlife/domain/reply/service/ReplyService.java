package com.secondlife.domain.reply.service;

import com.secondlife.domain.comment.entity.Comment;
import com.secondlife.domain.comment.repository.CommentRepository;
import com.secondlife.domain.reply.dto.request.ReplyRegistRequestDto;
import com.secondlife.domain.reply.dto.request.ReplyUpdateRequestDto;
import com.secondlife.domain.reply.dto.response.ReplyResponseDto;
import com.secondlife.domain.reply.entity.Reply;
import com.secondlife.domain.reply.repository.ReplyRepository;
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
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    // Id로 Reply 조회
    public ReplyResponseDto findReplyById(Long id) {

        Reply findReply = replyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 Reply가 존재하지 않습니다."));

        return new ReplyResponseDto(findReply);
    }

    // 전체 Reply 조회
    public List<ReplyResponseDto> findReplyList() {

        List<ReplyResponseDto> findReplyResponseDtoList = new ArrayList<>();

        List<Reply> findReplyList = replyRepository.findAll();

        for (Reply reply : findReplyList)
            findReplyResponseDtoList.add(new ReplyResponseDto(reply));

        return findReplyResponseDtoList;
    }

    // 특정 Comment에 달린 Reply 조회
    public List<ReplyResponseDto> findReplyListByCommentId(Long commentId) {

        List<ReplyResponseDto> findReplyResponseDtoList = new ArrayList<>();

        List<Reply> findReplyList = replyRepository.findAllReplyListByCommentId(commentId);

        for (Reply reply : findReplyList)
            findReplyResponseDtoList.add(new ReplyResponseDto(reply));

        return findReplyResponseDtoList;
    }

    // Reply 등록
    @Transactional
    public Long registReply(Long userId, Long commentId, ReplyRegistRequestDto replyRegistRequestDto) {

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 User가 존재하지 않습니다."));

        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Comment가 존재하지 않습니다."));

        replyRegistRequestDto.setUser(findUser);
        replyRegistRequestDto.setComment(findComment);

        Reply registReply = replyRegistRequestDto.toEntity();

        replyRepository.save(registReply);

        return registReply.getId();
    }

    // Reply 수정
    @Transactional
    public Long updateReply(Long id, ReplyUpdateRequestDto replyUpdateRequestDto) {

        Reply findReply = replyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 Reply가 존재하지 않습니다."));

        return findReply.update(replyUpdateRequestDto);
    }

    // Reply  삭제
    @Transactional
    public Long deleteReply(Long id) {

        replyRepository.deleteById(id);

        return id;
    }
}
