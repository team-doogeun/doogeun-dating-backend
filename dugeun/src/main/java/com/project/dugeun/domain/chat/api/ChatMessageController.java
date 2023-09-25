package com.project.dugeun.domain.chat.api;

import com.project.dugeun.domain.chat.application.ChatMessageService;
import com.project.dugeun.domain.chat.dto.ChatMessageForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.WsRemoteEndpointAsync;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat/send")
    public void sendMsg(ChatMessageForm message) {
        String receiver = message.getReceiver();
        Long chatRoomId = message.getChatRoomId();
        log.info(chatRoomId.toString());
        log.info(receiver);
        log.info(message.getMessage());
        log.info(message.getSender());
        chatMessageService.save(message);
        simpMessagingTemplate.convertAndSend("/topic/" + chatRoomId,message);
    }

}