package com.project.dugeun.domain.chat.dao;

import com.project.dugeun.domain.chat.domain.ChatRoom;
import com.project.dugeun.domain.chat.domain.ChatRoomJoin;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin,Long> {
    List<ChatRoomJoin> findByUser(User user);
    List<ChatRoomJoin> findByChatRoom(ChatRoom chatRoom);
}