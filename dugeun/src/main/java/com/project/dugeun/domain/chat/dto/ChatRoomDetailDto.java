package com.project.dugeun.domain.chat.dto;

import com.project.dugeun.domain.chat.domain.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDetailDto {
    private Long senderId;
    private String senderName;
    private String senderUserId;
    private String receiverName;
    private List<ChatMessage> messages;
    private Long chatRoomId;

}