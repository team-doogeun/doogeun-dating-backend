package com.project.dugeun.domain.dateChat.dateChatMessage.application;

import com.project.dugeun.domain.dateChat.daetChatRoom.application.DateChatRoomService;
import com.project.dugeun.domain.dateChat.daetChatRoom.domain.DateChatRoom;
import com.project.dugeun.domain.dateChat.dateChatMember.domain.DateChatMember;
import com.project.dugeun.domain.dateChat.dateChatMessage.dao.DateChatMessageRepository;
import com.project.dugeun.domain.dateChat.dateChatMessage.domain.DateChatMessage;
import com.project.dugeun.domain.dateChat.dateChatMessage.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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


    // 주어진 채팅 방에서 주어진 사용자에게서 받은 특정 ChatMessage의 ID 이후의 새로운 채팅 메시지 목록이 반환
    public List<ChatMessageDto> getByChatRoomIdAndUserIdAndFromId(Long roomId, String userId,Long fromId){

        DateChatRoom chatRoom = dateChatRoomService.findById(roomId);
        //주어진 사용자 ID가 해당 채팅 방의 멤버 중에 있는지 확인
        //해당 채팅 방에 접근 권한이 없는 경우 예외 발생
        chatRoom.getDateChatMembers().stream()
                .filter(dateChatMember -> dateChatMember.getUser().getUserId().equals(userId))
                .findFirst()
                .orElseThrow();

        List<DateChatMessage> chatMessages = dateChatMessageRepository.findByDateChatRoomId(roomId);
        List<DateChatMessage> list = chatMessages.stream()
                .filter(chatMessage -> chatMessage.getId() > fromId) //이전에 가져온 메시지들보다 새로운 메시지들만을 선택 ( fromId보다 큰 ID를 가진 채팅 메시지드을 필터링 )
                .sorted(Comparator.comparing(DateChatMessage::getId)) // 필터링된 메시지들을 채팅 메시지의 ID를 기준으로 오름차순 정렬
                .collect(Collectors.toList());

        return ChatMessageDto.fromChatMessages(list);
    }

}
