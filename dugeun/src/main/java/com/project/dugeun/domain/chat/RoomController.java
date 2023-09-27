package com.project.dugeun.domain.chat;

import com.project.dugeun.domain.chat.dto.ChatRoomDetailRequestDto;
import com.project.dugeun.domain.chat.dto.ChatRoomDetailResponseDto;
import com.project.dugeun.domain.chat.dto.ChatStartRequestDto;
import com.project.dugeun.domain.chat.dto.ChatStartResponseDto;
import com.project.dugeun.domain.finalMatch.application.FinalMatchService;
import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.hibernate.loader.entity.NaturalIdEntityJoinWalker;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoomController {

    private final ChatService chatService;
    private final JwtProvider jwtProvider;
    private final FinalMatchService finalMatchService;
    private final UserRepository userRepository;
    private final FinalMatchRepository finalMatchRepository;

    // 해당 채팅방 roomId, chatList 가져오기
    @GetMapping("/chatInfo")
    public ResponseEntity<ChatRoomDetailResponseDto> joinRoom(@RequestParam("userId")String userId,@RequestParam("roomId")Long roomId, @RequestHeader(value = "Authorization") String token) {

        List<Chat> chatList = chatService.findAllChatByRoomId(roomId);
        Claims claims = jwtProvider.parseJwtToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", claims.getSubject());

        if (!userId.equals(claims.getSubject())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        ChatRoomDetailResponseDto chatRoomDetailResponseDto =new ChatRoomDetailResponseDto(roomId,chatList);

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