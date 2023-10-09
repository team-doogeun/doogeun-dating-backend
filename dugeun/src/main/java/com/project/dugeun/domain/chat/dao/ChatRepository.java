package com.project.dugeun.domain.chat.dao;


import com.project.dugeun.domain.chat.domain.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Long> {
    List<Chat> findAllByRoomId(Long roomId);
}