package com.project.dugeun.domain.dateChat.dateChatMessage.application;

import com.project.dugeun.domain.dateChat.daetChatRoom.application.DateChatRoomService;
import com.project.dugeun.domain.dateChat.daetChatRoom.domain.DateChatRoom;
import com.project.dugeun.domain.dateChat.dateChatMember.domain.DateChatMember;
import com.project.dugeun.domain.dateChat.dateChatMessage.dao.DateChatMessageRepository;
import com.project.dugeun.domain.dateChat.dateChatMessage.domain.DateChatMessage;
import com.project.dugeun.domain.dateChat.dateChatMessage.dto.DateChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DateChatMessageService {

    private final DateChatMessageRepository dateChatMessageRepository;
    private final DateChatRoomService dateChatRoomService;

    public DateChatMessage createAndSave(String content,String senderId,Long dateChatRoomId){
        DateChatRoom dateChatRoom = dateChatRoomService.findById(dateChatRoomId);

        DateChatMember sender = dateChatRoom.getDateChatMembers().stream()
                .filter(dateChatMember -> dateChatMember.getUser().getUserId().equals(senderId))
                .findFirst()
                .orElseThrow();

        DateChatMessage dateChatMessage = DateChatMessage.create(content,sender,dateChatRoom);

        return dateChatMessageRepository.save(dateChatMessage);
    }



}
