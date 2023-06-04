package com.project.dugeun.domain.groupblind.api;


import com.project.dugeun.domain.groupblind.application.GroupBlindService;
import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.dto.RoomSaveRequestDto;
import com.project.dugeun.domain.groupblind.dto.RoomSaveResponseDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
@Setter
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GroupBlindController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupBlindRepository groupBlindRepository;

    @Autowired
    private GroupBlindService groupBlindService;

    @CrossOrigin(origins = "http://www.localhost:3000")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{userId}/new")
    public ResponseEntity createRoom(@PathVariable String userId,  @Valid @RequestBody RoomSaveRequestDto room, Authentication authentication){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // userId가 본인일 겨우에만 해당 방 만들 수 있도록 검증
        if(!userId.equals(authentication.getName()))
        {
            String responseMessage = "미팅방을 만들 수 없습니다";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

        GroupBlindRoom savedRoom =  groupBlindService.createMeetingRoom(room, authentication.getName());

        EntityModel<RoomSaveResponseDto> entityModel = EntityModel.of(new RoomSaveResponseDto(savedRoom));

        return ResponseEntity.ok(entityModel);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{userId}/delete/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable Integer roomId, Principal principal) {

        // Delete the meeting room
        boolean deleted = groupBlindService.deleteMeetingRoom(roomId, principal.getName());

        if (deleted) {
            return ResponseEntity.ok("Meeting room deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 다른 사용자가 URL을 통해 미팅방에 입장하는 것을 막기 위해 본인인지를 검증하는 부분을 추가
    // Spring Security의 Principal 객체를 사용하여 현재 인증된 사용자의 정보 가져오기
    @PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
    @PostMapping("/{userId}/{roomId}")
    public ResponseEntity enterRoom(@PathVariable Integer roomId, Principal principal){

        // userId가 본인일 경우에만 해당 방에 들어갈 수 있도록 검증
        // 해당 미팅방에는 본인만이 접근할 수 있도록 보장
        groupBlindService.enter(groupBlindRepository.findByRoomId(roomId), userRepository.findByUserId(principal.getName()));

        String responseMessage = "미팅방에 입장하였습니다";
        return ResponseEntity.ok(responseMessage);
    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/{userId}/{roomId}/exit")
//    public ResponseEntity exit(@PathVariable Integer roomId, Principal principal){
//
//        groupBlindService.exit(groupBlindRepository.findByRoomId(roomId), userRepository.findByUserId(principal.getName()));
//
//        // 응답처리
//        EntityModel<ExitRoomResponseDto> entityModel = EntityModel.of(new ExitRoomResponseDto((rq.getMember())));
//        return ResponseEntity.ok(entityModel);
//    }
}