package com.project.dugeun.domain.dateChat.daetChatRoom.application;

import com.project.dugeun.domain.dateChat.daetChatRoom.dao.DateChatRoomRepository;
import com.project.dugeun.domain.dateChat.daetChatRoom.domain.DateChatRoom;
import com.project.dugeun.domain.dateChat.daetChatRoom.dto.DateChatRoomDto;
import com.project.dugeun.domain.dateChat.dateChatMember.application.DateChatMemberService;
import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.user.application.UserService;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DateChatRoomService {

    private final FinalMatchRepository finalMatchRepository;
    private final DateChatRoomRepository dateChatRoomRepository;
    private final UserService userService;
    private final DateChatMemberService dateChatMemberService;


    @Transactional
    public DateChatRoom createAndConnect(FinalMatch finalMatch)
    {

        DateChatRoom dateChatRoom = DateChatRoom.create(finalMatch);
        DateChatRoom savedChatRoom = dateChatRoomRepository.save(dateChatRoom);

        log.info("savedDateChatRoom = {}",savedChatRoom);

        savedChatRoom.addChatUser(finalMatch.getUser1());
        savedChatRoom.addChatUser(finalMatch.getUser2());

        return savedChatRoom;

    }

    public List<DateChatRoom> findAll()
    {
     return dateChatRoomRepository.findAll();
    }


    public DateChatRoom findById(Long roomId){
        return dateChatRoomRepository.findById(roomId).orElseThrow();
    }

    // 모든 ChatRoom들을 가져와서 해당 userId가 속한 ChatRoom들 필털이
    public List<DateChatRoom> findRoomsByUserId(String userId){
        List<DateChatRoom> rooms = dateChatRoomRepository.findAll();
    }

}
