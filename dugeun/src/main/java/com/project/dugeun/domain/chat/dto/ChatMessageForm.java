package com.project.dugeun.domain.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageForm {
    private Long ChatRoomId;
    private String receiver; // receiver userId
    private String sender;  // sender userId
    private String message;
}