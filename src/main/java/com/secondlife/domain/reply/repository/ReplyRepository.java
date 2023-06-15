package com.secondlife.domain.reply.repository;

import com.secondlife.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllReplyListByCommentId(@Param("commentId") Long commentId);
}
