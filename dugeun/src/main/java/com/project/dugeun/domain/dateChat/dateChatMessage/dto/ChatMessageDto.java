package com.project.dugeun.domain.dateChat.dateChatMessage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.dugeun.domain.dateChat.dateChatMessage.domain.DateChatMessage;
import com.project.dugeun.domain.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDto {

    @JsonProperty("message_id")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("sender")
    private UserDto sender;


    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static ChatMessageDto fromChatMessage(DateChatMessage chatMessage) {

        UserDto userDto = UserDto.fromUser(chatMessage.getSender().getUser());

        return ChatMessageDto.builder()
                .id(chatMessage.getId())
                .sender(userDto)
                .content(chatMessage.getContent())
                .createdAt(chatMessage.getCreateDate())
                .updatedAt(chatMessage.getModifyDate())
                .build();
    }

    public static List<ChatMessageDto> fromChatMessages(List<DateChatMessage> messages) {
        return messages.stream()
                .map(ChatMessageDto::fromChatMessage)
                .collect(Collectors.toList());
    }

}
