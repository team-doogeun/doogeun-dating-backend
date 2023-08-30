package com.project.dugeun.domain.chat.api;

import com.project.dugeun.domain.chat.application.ChatMessageService;
import com.project.dugeun.domain.chat.dto.ChatMessageForm;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat/send")
    public void sendMsg(ChatMessageForm message) {
        String receiver = message.getReceiver();
        System.out.println(receiver);
        chatMessageService.save(message);
        simpMessagingTemplate.convertAndSend("/topic/" + receiver,message);
    }

}