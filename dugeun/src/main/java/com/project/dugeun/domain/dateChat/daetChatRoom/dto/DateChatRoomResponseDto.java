package com.project.dugeun.domain.dateChat.daetChatRoom.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.dugeun.domain.dateChat.dateChatMember.domain.DateChatMember;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DateChatRoomResponseDto {
    // 2명의 사용자의 정보 response로
    private DateChatMember firstUser;
    private DateChatMember secondUser;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
