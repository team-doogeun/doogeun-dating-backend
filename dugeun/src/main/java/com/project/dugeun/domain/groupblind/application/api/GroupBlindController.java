package com.project.dugeun.domain.groupblind.application.api;

import com.project.dugeun.domain.groupblind.application.GroupBlindService;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.Participant;
import com.project.dugeun.domain.groupblind.dto.*;
import com.project.dugeun.domain.user.application.UserService;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@Setter
public class GroupBlindController {

    private final UserService userService;
    private final GroupBlindService groupBlindService;
    private final JwtProvider jwtProvider;

    @PostMapping("group/{userId}/new")
    public ResponseEntity createRoom(@PathVariable String userId, @RequestHeader(value = "Authorization") String token, @Valid @RequestBody RoomSaveRequestDto room) {

        Claims claims = jwtProvider.parseJwtToken(token);

        // userId가 본인일 겨우에만 해당 방 만들 수 있도록 검증
        if (!userId.equals(claims.getSubject())) {
            return getStringResponsMessage("미팅방을 만들 수 없습니다", HttpStatus.FORBIDDEN);
        }

        GroupBlindRoom savedRoom = groupBlindService.createMeetingRoom(room, claims.getSubject());

        EntityModel<RoomSaveResponseDto> entityModel = EntityModel.of(new RoomSaveResponseDto(savedRoom));

        return ResponseEntity.ok(entityModel);

    }


    @PostMapping("group/{roomId}/delete")
    public ResponseEntity<String> deleteRoom(@PathVariable Integer roomId, @RequestHeader(value = "Authorization") String token) {
        Claims claims = jwtProvider.parseJwtToken(token);

        // Check if the user is the host of the meeting room
        boolean isHost = groupBlindService.isHostOfMeetingRoom(roomId, claims.getSubject());
        if (!isHost) {
            return getStringResponsMessage("미팅방을 삭제할 수 있는 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        boolean deleted = groupBlindService.deleteMeetingRoom(roomId);
        if (deleted) {
            return ResponseEntity.ok("Meeting room deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("group/{roomId}")
    public ResponseEntity enterRoom(@PathVariable Integer roomId, @RequestHeader(value = "Authorization") String token) {

        Claims claims = jwtProvider.parseJwtToken(token);
        String userId = claims.getSubject();

        // Find the user
        User user = userService.findUserByUserId(userId);
        if (user == null) {
            return getStringResponsMessage("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        // Find the meeting room
        GroupBlindRoom meetingRoom = groupBlindService.getRoomByRoomId(roomId);
        if (meetingRoom == null) {
            return getStringResponsMessage("미팅방을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        boolean roomIsFull = meetingRoom.getPresentMale() == meetingRoom.getCapacityMale() && meetingRoom.getPresentFemale() == meetingRoom.getCapacityFemale();
        boolean userExists = meetingRoom.getParticipants().stream()
                .anyMatch(participant -> participant.getUser().getUserId().equals(userId));

        if (roomIsFull) {
            return getStringResponsMessage("미팅방이 가득 찼습니다.", HttpStatus.ACCEPTED);
        }

        if (userExists) {
            return getStringResponsMessage("이미 해당 미팅방에 입장한 유저입니다.", HttpStatus.ACCEPTED);
        }

        groupBlindService.enter(meetingRoom, user);

        String responseMessage = "미팅방에 입장하였습니다.";
        return ResponseEntity.ok(responseMessage);
    }

    private ResponseEntity getStringResponsMessage(String s, HttpStatus notFound) {
        String responseMessage = s;
        return ResponseEntity.status(notFound).body(responseMessage);
    }

    @PostMapping("group/{roomId}/exit")
    public ResponseEntity<?> exitroom(@PathVariable Integer roomId, @RequestHeader(value = "Authorization") String token) {
        Claims claims = jwtProvider.parseJwtToken(token);
        String userId = claims.getSubject();

        // Find the user
        User user = userService.findUserByUserId(userId);
        if (user == null) {
            return getStringResponsMessage("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        GroupBlindRoom groupBlindRoom = groupBlindService.getRoomByRoomId(roomId);
        if (groupBlindRoom == null) {
            return getStringResponsMessage("미팅방을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
        Participant participant = groupBlindRoom.getParticipants().stream()
                .filter(p -> p.getUser().getUserId().equals(userId))
                .findFirst()
                .orElse(null);

        if (participant == null) {
            return getStringResponsMessage("미팅방에 참여한 유저가 아닙니다.", HttpStatus.FORBIDDEN);
        }

        groupBlindService.exit(groupBlindRoom, participant);

        String responseMessage = "미팅방에서 나갔습니다.";
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/group")
    public ResponseEntity<List<GroupBlindDto>> getMeetingRooms() {
        List<GroupBlindDto> meetingRooms = groupBlindService.getAllMeetingRooms();
        return ResponseEntity.ok(meetingRooms);
    }


    @GetMapping("/group/{roomId}/info")
    public ResponseEntity<?> getInfoRoom(@PathVariable Integer roomId, @RequestHeader(value = "Authorization") String token) {
        GroupInfoResponseDto responseDto = groupBlindService.getGroupInfo(roomId);
        return ResponseEntity.ok(responseDto);
    }


    @PostMapping("/group/{roomId}/achieve")
    public ResponseEntity<?> startMeeting(@PathVariable Integer roomId, @RequestHeader("Authorization") String token) {

        Claims claims = jwtProvider.parseJwtToken(token);
        String userId = claims.getSubject();

        User user = userService.findUserByUserId(userId);
        if (user == null) {
            return getStringResponsMessage("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        List<Map<String, String>> participantExternalIds = groupBlindService.startMeeting(roomId, userId);
        if (participantExternalIds == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(participantExternalIds);
    }
}