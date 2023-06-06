package com.project.dugeun.domain.groupblind.api;


import com.project.dugeun.domain.base.rq.Rq;
import com.project.dugeun.domain.groupblind.application.GroupBlindService;
import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.dto.ExitRoomResponseDto;
import com.project.dugeun.domain.groupblind.dto.RoomSaveRequestDto;
import com.project.dugeun.domain.groupblind.dto.RoomSaveResponseDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequiredArgsConstructor
@Setter
public class GroupBlindController {

    private final Rq rq;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupBlindRepository groupBlindRepository;
    @Autowired
    private GroupBlindService groupBlindService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("group/{userId}/new")
    public ResponseEntity createRoom(@PathVariable String userId,@RequestHeader(value="Authorization")String token, @Valid @RequestBody RoomSaveRequestDto room){

        Claims claims = jwtProvider.parseJwtToken(token);

        // userId가 본인일 겨우에만 해당 방 만들 수 있도록 검증
        if(!userId.equals(claims.getSubject()))
        {
            String responseMessage = "미팅방을 만들 수 없습니다";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

        EntityModel<RoomSaveResponseDto> entityModel = null;
        GroupBlindRoom savedRoom =  groupBlindService.createMeetingRoom(room, claims.getSubject());
        entityModel = EntityModel.of(new RoomSaveResponseDto(savedRoom));

        return ResponseEntity.ok(entityModel);

    }


    @DeleteMapping("group/delete/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable Integer roomId,@RequestHeader(value="Authorization")String token) {

        Claims claims = jwtProvider.parseJwtToken(token);
        // Delete the meeting room
        boolean deleted = groupBlindService.deleteMeetingRoom(roomId, claims.getSubject());

        if (deleted) {
            return ResponseEntity.ok("Meeting room deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("group/{roomId}")
    public ResponseEntity enterRoom(@PathVariable Integer roomId, @RequestHeader(value="Authorization")String token){

        Claims claims = jwtProvider.parseJwtToken(token);
        // userId가 본인일 경우에만 해당 방에 들어갈 수 있도록 검증
        // 해당 미팅방에는 본인만이 접근할 수 있도록 보장
        groupBlindService.enter(groupBlindRepository.findByRoomId(roomId),
                userRepository.findByUserId(claims.getSubject()));

        String responseMessage = "미팅방에 입장하였습니다";
        return ResponseEntity.ok(responseMessage);
    }


    @PostMapping("group/{roomId}/exit")
    public ResponseEntity exit(@PathVariable Integer roomId, @RequestHeader(value="Authorization")String token){
        Claims claims = jwtProvider.parseJwtToken(token);
        groupBlindService.exit(groupBlindRepository.findByRoomId(roomId), userRepository.findByUserId(claims.getSubject()));

        // 응답처리
        EntityModel<ExitRoomResponseDto> entityModel = EntityModel.of(new ExitRoomResponseDto((rq.getMember())));
        return ResponseEntity.ok(entityModel);

    }
}