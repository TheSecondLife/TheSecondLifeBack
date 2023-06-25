package com.secondlife.domain.friend.repository;

import com.secondlife.domain.friend.dto.response.FriendResponseDto;
import com.secondlife.domain.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select f.areWeFriend from Friend f " +
            "where f.requestUser.id = :requestUserId and f.responseUser.id = :responseUserId")
    Optional<Boolean> findFriendByRequestUserIdAndResponseUserId
            (@Param("requestUserId") Long requestUserId, @Param("responseUserId") Long responseUserId);


    @Query("select f from Friend f " +
            "where f.requestUser.id = :requestUserId and f.responseUser.id = :responseUserId")
    Optional<Friend> findFriendRequestByRequestUserIdAndResponseUserId
            (@Param("requestUserId") Long requestUserId, @Param("responseUserId") Long responseUserId);



    @Query("select new com.secondlife.domain.friend.dto.response.FriendResponseDto(f.responseUser)  " +
            "from Friend f " +
            "where f.areWeFriend = true and f.requestUser.id = :userId")
    List<FriendResponseDto> findFriendListByUserId(@Param("userId") Long userId);
}
