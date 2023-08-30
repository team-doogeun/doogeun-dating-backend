package com.project.dugeun.domain.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDto {
    private Long senderId;
    private String senderName;
    private String senderUserId;
    private List<ChatRoomForm> chatRooms;
}