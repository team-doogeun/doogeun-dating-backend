package com.project.dugeun.domain.chat.api;

import com.project.dugeun.domain.chat.application.ChatRoomJoinService;
import com.project.dugeun.domain.chat.application.ChatRoomService;
import com.project.dugeun.domain.chat.domain.ChatMessage;
import com.project.dugeun.domain.chat.domain.ChatRoom;
import com.project.dugeun.domain.chat.dto.ChatRequestDto;
import com.project.dugeun.domain.chat.dto.ChatRoomDetailDto;
import com.project.dugeun.domain.user.application.UserService;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final UserService userService;
    private final ChatRoomJoinService chatRoomJoinService;
    private final ChatRoomService chatRoomService;

    // 마이페이지의 최종 매칭에서 "좋아요"버튼 눌렀을 때
    @PostMapping(value = {"/finalMatch/personalChat"})
    @ResponseBody
    public ResponseEntity<ChatRoomDetailDto> goChat(@RequestBody ChatRequestDto chatRequestDto, @RequestHeader(value="Authorization")String token) {

        // 찾은 2명의 유저를 이용하여 ChatRoom을 찾아내는 메서드를 ChatRoomJoinRepository에서 구현
        User user = userService.findUserByUserId(chatRequestDto.getUserId());
        User anotherUser = userService.findUserByUserId(chatRequestDto.getAnotherUserId());
    // 이렇게 하면 아래의 chatRoomId를 통해 받아올 필요는 없어지므로 dto에 chatRoomId르 넣어서 넘겨줄 필요가 없다.
       Optional<ChatRoom> opt = chatRoomService.findChatRoomByTwoUser(user, anotherUser);
       ChatRoom chatRoom = opt.get();


        List<ChatMessage> messages = chatRoom.getMessages();
        Collections.sort(messages, (t1, t2) -> {
            if (t1.getId() > t2.getId()) return -1;
            else return 1;
        });

        ChatRoomDetailDto chatRoomDetailDto = new ChatRoomDetailDto();

        chatRoomDetailDto.setSenderName(user.getName());
        chatRoomDetailDto.setSenderId(user.getId());
        chatRoomDetailDto.setSenderUserId(user.getUserId());
        chatRoomDetailDto.setMessages(messages);
        chatRoomDetailDto.setChatRoomId(chatRoom.getId());
        chatRoomDetailDto.setReceiverName(anotherUser.getUserId());

        return ResponseEntity.ok().body(chatRoomDetailDto);

    }

}