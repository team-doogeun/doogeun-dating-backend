package com.project.dugeun.domain.dateChat.daetChatRoom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.dugeun.domain.dateChat.daetChatRoom.domain.DateChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateChatRoomDto {

    @JsonProperty("date_chat_room_id")
    private Long id;


    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;


    public static DateChatRoomDto fromDateChatRoom(DateChatRoom dateChatRoom, boolean isNew)
    {

        return DateChatRoomDto.builder()
                .id(dateChatRoom.getId())
                .createdAt(dateChatRoom.getCreateDate())
                .updatedAt(dateChatRoom.getModifyDate())
                .build();
    }
}
