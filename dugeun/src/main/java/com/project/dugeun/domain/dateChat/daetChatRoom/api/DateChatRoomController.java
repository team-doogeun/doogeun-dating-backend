package com.project.dugeun.domain.dateChat.daetChatRoom.api;

import com.project.dugeun.domain.dateChat.daetChatRoom.domain.DateChatRoom;
import com.project.dugeun.domain.dateChat.daetChatRoom.dto.DateChatRoomResponseDto;
import com.project.dugeun.domain.dateChat.daetChatRoom.application.DateChatRoomService;
import com.project.dugeun.domain.dateChat.dateChatMember.application.DateChatMemberService;
import com.project.dugeun.domain.dateChat.dateChatMember.domain.DateChatMember;
import com.project.dugeun.domain.dateChat.dateChatMessage.application.DateChatMessageService;
import com.project.dugeun.domain.finalMatch.application.FinalMatchService;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
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

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dateChat")
public class DateChatRoomController {

    private final DateChatRoomService dateChatRoomService;
    private final DateChatMessageService dateChatMessageService;
    private final SimpMessageSendingOperations template;
    private final DateChatMemberService dateChatMemberService;
    private final FinalMatchService finalMatchService;
    private final JwtProvider jwtProvider;

    // 마이페이지의 최종 매칭 화면에서 채팅방에 들어왔을 때
    @GetMapping("/{userId}/{finalMatchId}")
    public ResponseEntity<Object> startDateChatRoom(@PathVariable String userId, @PathVariable Long finalMatchId, @RequestHeader(value = "Authorization") String token){

        Claims claims = jwtProvider.parseJwtToken(token);

        // 응답 헤더에 클레임 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", claims.getSubject());

        // userId와 넘겨받은 token의 subject(userId)와 같으면 인증완료
        if (!userId.equals(claims.getSubject())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }


        Optional<FinalMatch> optionalFinalMatch = finalMatchService.findById(finalMatchId);
        if (optionalFinalMatch.isPresent()) {
            FinalMatch finalMatch = optionalFinalMatch.get();
            DateChatRoom dateChatRoom = dateChatRoomService.createAndConnect(finalMatch);

            DateChatMember firstUser = DateChatMember.builder()
                    .user(finalMatch.getUser1())
                    .dateChatRoom(dateChatRoom)
                    .build();

            DateChatMember secondUser = DateChatMember.builder()
                    .user(finalMatch.getUser2())
                    .dateChatRoom(dateChatRoom)
                    .build();


            DateChatRoomResponseDto dateChatRoomResponseDto = DateChatRoomResponseDto.builder()
                    .createdAt(finalMatch.getCreateDate())
                    .updatedAt(finalMatch.getModifyDate())
                    .firstUser(firstUser)
                    .secondUser(secondUser)
                    .build();

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(dateChatRoomResponseDto);

        } else {
            // 예외 처리
        }


        return null;
    }



}
