package com.secondlife.domain.chat.repository;

import com.secondlife.domain.chat.entity.ChatRoom;
import com.secondlife.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("SELECT count(c) FROM ChatRoom c WHERE c.userA = :userA and c.userB = :userB")
    int isExist(@Param("userA") User userA, @Param("userB") User userB);

}
