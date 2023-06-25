package com.secondlife.domain.user.repository;

import com.secondlife.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    User findByEmail(String email);
    Optional<User> findByEmail(String email);

    User findUserById(Long Id);

    @Override
    @EntityGraph(attributePaths = {"postList", "commentList", "replyList", "interestList", "chatRoomList", "requestFriendList", "responseFriendList"})
    List<User> findAll();

    @Override
    @EntityGraph(attributePaths = {"postList", "commentList", "replyList", "interestList", "chatRoomList", "requestFriendList", "responseFriendList"})
    Optional<User> findById(Long aLong);

    @Override
    @EntityGraph(attributePaths = {"postList", "commentList", "replyList", "interestList", "chatRoomList", "requestFriendList", "responseFriendList"})
    <S extends User> S save(S entity);

    @Override
    @EntityGraph(attributePaths = {"postList", "commentList", "replyList", "interestList", "chatRoomList", "requestFriendList", "responseFriendList"})
    void deleteById(Long aLong);
}
