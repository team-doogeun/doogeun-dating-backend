package com.project.dugeun.domain.groupblind.api;


import com.project.dugeun.domain.base.rq.Rq;
import com.project.dugeun.domain.groupblind.application.GroupBlindService;
import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRole;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.Participant;
import com.project.dugeun.domain.groupblind.dto.*;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


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

        GroupBlindRoom savedRoom =  groupBlindService.createMeetingRoom(room, claims.getSubject());

        EntityModel<RoomSaveResponseDto> entityModel = EntityModel.of(new RoomSaveResponseDto(savedRoom));

        return ResponseEntity.ok(entityModel);

    }


    @DeleteMapping("group/{roomId}/delete")
    public ResponseEntity<String> deleteRoom(@PathVariable Integer roomId, @RequestHeader(value="Authorization") String token) {

        Claims claims = jwtProvider.parseJwtToken(token);

        GroupBlindRoom groupBlindRoom = groupBlindRepository.findByRoomId(roomId);
        if (groupBlindRoom == null) {
            return ResponseEntity.notFound().build();
        }

        // userId가 호스트인지 확인
        boolean isHost = groupBlindRoom.getParticipants().stream()
                .anyMatch(p -> p.getGroupBlindRole() == GroupBlindRole.HOST && p.getUser().getUserId().equals(claims.getSubject()));

        if (!isHost) {
            String responseMessage = "미팅방을 삭제할 수 있는 권한이 없습니다";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

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
    public ResponseEntity exitroom(@PathVariable Integer roomId, @RequestHeader(value="Authorization")String token){
        Claims claims = jwtProvider.parseJwtToken(token);
        groupBlindService.exit(groupBlindRepository.findByRoomId(roomId), userRepository.findByUserId(claims.getSubject()));

        // 응답처리
        EntityModel<ExitRoomResponseDto> entityModel = EntityModel.of(new ExitRoomResponseDto((rq.getMember())));
        return ResponseEntity.ok(entityModel);
    }

    @GetMapping("/group")
    public ResponseEntity<List<GroupBlindDto>> getMeetingRooms() {
        List<GroupBlindRoom> meetingRooms = groupBlindService.getAllMeetingRooms();
        List<GroupBlindDto> roomDto = meetingRooms.stream()
                .map(GroupBlindDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roomDto);
    }

    @GetMapping("group/{roomId}/info")
    public ResponseEntity<GroupInfoResponseDto> getInfo(@PathVariable Integer roomId) {
        GroupBlindRoom groupBlindRoom = groupBlindRepository.findByRoomId(roomId);
        if (groupBlindRoom == null) {
            return ResponseEntity.notFound().build();
        }

        List<Participant> participants = groupBlindRoom.getParticipants();
        List<UserInfoDto> members = participants.stream()
                .map(participant -> {
                    User user = participant.getUser();
                    return new UserInfoDto(user.getAge(), user.getDetailProfile().getDepartment(), user.getGender());
                })
                .collect(Collectors.toList());

        GroupInfoResponseDto responseDto = new GroupInfoResponseDto(
                members,
                groupBlindRoom.getPresentMale(),
                groupBlindRoom.getPresentFemale(),
                groupBlindRoom.getGroupBlindIntroduction(),
                groupBlindRoom.getHostId(),
                groupBlindRoom.getTitle()
        );
        return ResponseEntity.ok(responseDto);
    }
}