package com.project.dugeun.domain.chat.application;

import com.project.dugeun.domain.chat.dao.ChatMessageRepository;
import com.project.dugeun.domain.chat.domain.ChatMessage;
import com.project.dugeun.domain.chat.dto.ChatMessageForm;
import com.project.dugeun.domain.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final UserService userService;
    private final ChatRoomService chatRoomService;
    @Transactional
    public void save(ChatMessageForm message) {
        ChatMessage chatMessage = new ChatMessage(message.getMessage(), LocalDateTime.now(),chatRoomService.findById(message.getChatRoomId()).get()
                ,userService.findUserByUserId(message.getSender()));
        chatMessageRepository.save(chatMessage);
    }
}
