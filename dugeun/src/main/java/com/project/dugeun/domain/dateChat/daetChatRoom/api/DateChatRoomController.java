package com.project.dugeun.domain.dateChat.daetChatRoom.api;

import com.project.dugeun.domain.blindDate.dto.DateChatRoomResponseDto;
import com.project.dugeun.domain.dateChat.daetChatRoom.application.DateChatRoomService;
import com.project.dugeun.domain.dateChat.daetChatRoom.domain.DateChatRoom;
import com.project.dugeun.domain.dateChat.dateChatMember.application.DateChatMemberService;
import com.project.dugeun.domain.dateChat.dateChatMessage.application.DateChatMessageService;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dateChat")
public class DateChatRoomController {

    private final DateChatRoomService dateChatRoomService;
    private final DateChatMessageService dateChatMessageService;
    private final SimpMessageSendingOperations template;
    private final DateChatMemberService dateChatMemberService;
    private final JwtProvider jwtProvider;

    @GetMapping("/{userId}/{roomId}")
    public ResponseEntity<DateChatRoomResponseDto> showRooms(@PathVariable String userId,@PathVariable String anotherUserId, @RequestHeader(value = "Authorization") String token){

        Claims claims = jwtProvider.parseJwtToken(token);

        // 응답 헤더에 클레임 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", claims.getSubject());

        // userId와 넘겨받은 token의 subject(userId)와 같으면 인증완료
        if (!userId.equals(claims.getSubject())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }


    }



}
