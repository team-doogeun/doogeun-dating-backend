package com.project.dugeun.domain.chat.application;

import com.project.dugeun.domain.chat.dao.ChatMessageRepository;
import com.project.dugeun.domain.chat.dao.ChatRoomRepository;
import com.project.dugeun.domain.chat.domain.ChatMessage;
import com.project.dugeun.domain.chat.domain.ChatRoom;
import com.project.dugeun.domain.chat.dto.ChatMessageForm;
import com.project.dugeun.domain.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final UserService userService;
    private final ChatRoomService chatRoomService;
    private final ChatRoomRepository chatRoomRepository;
    @Transactional
    public void save(ChatMessageForm message) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(message.getChatRoomId());
        ChatRoom room = null;
        if(chatRoom.isPresent()){
        room = chatRoom.get();
        }else{
           throw new IllegalStateException("해당 roomId가 없습니다");
        }
        ChatMessage chatMessage = new ChatMessage(message.getMessage(), LocalDateTime.now(),room
                ,userService.findUserByUserId(message.getSender()));
        chatMessageRepository.save(chatMessage);
    }


}
