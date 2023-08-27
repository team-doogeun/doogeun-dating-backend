package com.project.dugeun.domain.dateChat.dateChatMessage.api;

import com.project.dugeun.domain.dateChat.dateChatMessage.application.DateChatMessageService;
import com.project.dugeun.domain.dateChat.dateChatMessage.dto.ChatMessageDto;
import com.project.dugeun.domain.dateChat.dateChatMessage.dto.request.ChatMessageRequest;
import com.project.dugeun.domain.dateChat.dateChatMessage.dto.response.SignalResponse;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Path;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DateChatMessageController {

    private final DateChatMessageService dateChatMessageService;
    private final JwtProvider jwtProvider;


    @MessageMapping("/chats/{roomId}/sendMessage")
    @SendTo("/topic/chats/{roomId}")
    public SignalResponse sendChatMessage(@DestinationVariable Long roomId, ChatMessageRequest request, @RequestHeader(value = "Authorization")String token)
    {
        log.info("content : {}", request.getContent());
        Claims claims = jwtProvider.parseJwtToken(token);
        dateChatMessageService.createAndSave(request.getContent(), claims.getSubject(), roomId);

        return SignalResponse.builder()
                .build();
    }

    @GetMapping("/usr/{userId}/chat/rooms/{roomId}/messages")
    @ResponseBody
    public ResponseEntity<List<ChatMessageDto>> findAll(@PathVariable Long roomId,@PathVariable String userId,@RequestParam(defaultValue = "0") Long fromId, @RequestHeader(value = "Authorization")String token)
    {
        Claims claims = jwtProvider.parseJwtToken(token);

        // 응답 헤더에 클레임 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", claims.getSubject());

        if (!userId.equals(claims.getSubject())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        List<ChatMessageDto> chatMessageDtos = dateChatMessageService.getByChatRoomIdAndUserIdAndFromId(roomId,claims.getSubject(),fromId);

        return ResponseEntity.ok()
                .headers(headers)
                .body(chatMessageDtos);

    }


}