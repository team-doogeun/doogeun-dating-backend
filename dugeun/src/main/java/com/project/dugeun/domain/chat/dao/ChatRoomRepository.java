package com.project.dugeun.domain.chat.dao;

import com.project.dugeun.domain.chat.domain.ChatRoom;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
   Optional<ChatRoom> findById(Long id);


   Optional<ChatRoom> findByChatRoomJoinUserInAndChatRoomJoinUserIn(User user1, User user2);

}