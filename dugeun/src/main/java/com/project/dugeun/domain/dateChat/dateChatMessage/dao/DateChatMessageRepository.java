package com.project.dugeun.domain.dateChat.dateChatMessage.dao;

import com.project.dugeun.domain.dateChat.dateChatMessage.domain.DateChatMessage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateChatMessageRepository extends JpaRepository<DateChatMessage, Long> {

    List<DateChatMessage> findByDateChatRoomId(Long roomId);
}
