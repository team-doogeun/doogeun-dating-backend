package com.project.dugeun.domain.chat.dao;

import com.project.dugeun.domain.chat.domain.ChatRoom;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {


   List<ChatRoom> findByChatRoomJoinsUserIn(List<User> users);
}