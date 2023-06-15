package com.secondlife.domain.comment.repository;

import com.secondlife.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @Query("select c from Comment c left join c.post p where p.id = :postId")
    List<Comment> findAllCommentListByPostId(@Param("postId") Long postId);

    @Override
    @EntityGraph(attributePaths = {"replyList"})
    List<Comment> findAll();

    @Override
    @EntityGraph(attributePaths = {"replyList"})
    Optional<Comment> findById(Long aLong);

    @Override
    @EntityGraph(attributePaths = {"replyList"})
    <S extends Comment> S save(S entity);

    @Override
    @EntityGraph(attributePaths = {"replyList"})
    void deleteById(Long aLong);
}
