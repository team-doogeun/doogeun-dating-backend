package com.project.dugeun.domain.chat;

import com.project.dugeun.domain.chat.dto.*;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoomController {

    private final ChatService chatService;
    private final JwtProvider jwtProvider;

    // 해당 채팅방 roomId, chatList 가져오기
    @GetMapping("/chatInfo")
    public ResponseEntity<ChatRoomDetailResponseDto> joinRoom(@RequestParam("userId")String userId,@RequestParam("roomId")Long roomId, @RequestHeader(value = "Authorization") String token) {
        List<Chat> chatList = chatService.findAllChatByRoomId(roomId);
        List<ChatMessage> chatMessageList = new ArrayList<>();

        // Chat 리스트를 반복하여 모든 Chat의 sender와 message를 senderMessageMap에 추가
        for (Chat chat : chatList) {
            ChatMessage chatInfo = ChatMessage.builder()
                    .roomId(roomId)
                    .sendDate(chat.getSendDate())
                    .sender(chat.getSender())
                    .message(chat.getMessage())
                    .build();
            chatMessageList.add(chatInfo); // ChatInfo를 리스트에 추가
        }

        Claims claims = jwtProvider.parseJwtToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", claims.getSubject());

        if (!userId.equals(claims.getSubject())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        ChatRoomDetailResponseDto chatRoomDetailResponseDto = new ChatRoomDetailResponseDto(roomId, chatMessageList);

        return ResponseEntity.ok()
                .headers(headers)
                .body(chatRoomDetailResponseDto);

    }


    // 채팅방 초기 생성
    @PostMapping("/chatStart")
    public ResponseEntity<ChatStartResponseDto> createRoom(@RequestBody ChatStartRequestDto chatStartRequestDto, @RequestHeader(value = "Authorization") String token) {
        Claims claims = jwtProvider.parseJwtToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", claims.getSubject());
        String userId = chatStartRequestDto.getUserId();
        String anotherUserId = chatStartRequestDto.getAnotherUserId();
        if (!userId.equals(claims.getSubject())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        Long roomId = chatService.findAndMakeChatRoom(userId, anotherUserId);
        ChatStartResponseDto chatStartResponseDto = new ChatStartResponseDto();
        chatStartResponseDto.setRoomId(roomId);
        return ResponseEntity.ok()
                .headers(headers)
                .body(chatStartResponseDto);


    }

    /**
     * 채팅방 리스트 보기
     */
//    @GetMapping("/roomList")
//    public String roomList(Model model) {
//        List<Room> roomList = chatService.findAllRoom();
//        model.addAttribute("roomList", roomList);
//        return "chat/roomList";
//    }

    /**
     * 방만들기 폼
     */
//    @GetMapping("/roomForm")
//    public String roomForm() {
//        return "chat/roomForm";
//    }

}