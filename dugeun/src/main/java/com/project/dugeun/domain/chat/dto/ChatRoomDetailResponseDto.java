package com.project.dugeun.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDetailResponseDto {

    private Long roomId;
    private List<ChatMessage> chatMessageList;

    //    private List<Long> chatIdList;
    //    private List<Chat> chatList;


}

