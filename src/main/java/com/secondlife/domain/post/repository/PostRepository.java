package com.secondlife.domain.post.repository;

import com.secondlife.domain.post.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Override
    @EntityGraph(attributePaths = {"commentList"})
    List<Post> findAll();

    @Override
    @EntityGraph(attributePaths = {"commentList"})
    Optional<Post> findById(Long aLong);

    @Override
    @EntityGraph(attributePaths = {"commentList"})
    <S extends Post> S save(S entity);

    @Override
    @EntityGraph(attributePaths = {"commentList"})
    void deleteById(Long aLong);
}
