package com.secondlife.domain.chat.repository;

import com.secondlife.domain.chat.entity.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Chat, String> {

    List<Chat> findByRoomId(String roomId);

}
