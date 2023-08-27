package com.project.dugeun.domain.dateChat.dateChatMember.application;

import com.project.dugeun.domain.dateChat.daetChatRoom.dao.DateChatRoomRepository;
import com.project.dugeun.domain.dateChat.daetChatRoom.domain.DateChatRoom;
import com.project.dugeun.domain.dateChat.dateChatMember.dao.DateChatMemberRepository;
import com.project.dugeun.domain.dateChat.dateChatMember.domain.DateChatMember;
import com.project.dugeun.domain.groupblind.domain.GroupBlindCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DateChatMemberService {

    private final DateChatMemberRepository dateChatMemberRepository;
    private final DateChatRoomRepository dateChatRoomRepository;

    public DateChatMember findById(Long dateChatUserId)
    {
        return dateChatMemberRepository.findById(dateChatUserId).orElseThrow();
    }

    public List<DateChatMember> findByDateChatRoomId(Long dateChatRoomId)
    {
        return dateChatMemberRepository.findByDateChatRoomId(dateChatRoomId);
    }
    
    public List<DateChatMember> findBDateChatMembersByChatRoomIdAndMemberId(Long roomId, Long memberId){
        DateChatRoom dateChatRoom = findByRoomId(roomId);
        DateChatMember dateChatMember = findDateChatMemberByMemberId(dateChatRoom, memberId);

        if(dateChatMember == null){
            return null;
        }

        return dateChatMemberRepository.findByDateChatRoomId(roomId);

    }

    public DateChatRoom findByRoomId(Long roomId){
        return dateChatRoomRepository.findById(roomId).orElseThrow();
    }

    private DateChatMember findDateChatMemberByMemberId(DateChatRoom dateChatRoom, Long memberId){
        return dateChatRoom.getDateChatMembers().stream()
                .filter(dateChatMember -> dateChatMember.getUser().getId().equals(memberId))
                .findFirst()
                .orElseThrow(null);
    }





}
