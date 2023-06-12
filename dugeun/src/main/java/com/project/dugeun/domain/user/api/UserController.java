package com.project.dugeun.domain.user.api;

import com.project.dugeun.domain.finalMatch.application.FinalMatchService;
import com.project.dugeun.domain.groupblind.application.GroupBlindService;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.Participant;
import com.project.dugeun.domain.groupblind.dto.GroupBlindDto;
import com.project.dugeun.domain.groupblind.dto.GroupInfoResponseDto;
import com.project.dugeun.domain.groupblind.dto.UserInfoDto;
import com.project.dugeun.domain.likeablePerson.application.LikeablePersonService;
import com.project.dugeun.domain.likeablePerson.dto.LikeRequestDto;
import com.project.dugeun.domain.user.application.UserService;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.dto.*;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final FinalMatchService finalMatchService;
    private final LikeablePersonService likeablePersonService;
    private final GroupBlindService groupBlindService;
    // 나가 좋아요 한 상대들 확인
    @GetMapping("/{userId}/blindDate/toLike")
    public ResponseEntity<List<ToLikeablePersonResponseDto>> getToLikeablePersons(@PathVariable String userId, @RequestHeader(value="Authorization")String token){

        Claims claims = jwtProvider.parseJwtToken(token);

        if(!userId.equals(claims.getSubject())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }
            List<ToLikeablePersonResponseDto> toLikeablePersons = userService.getToLikeablePersons(userId);
            return ResponseEntity.ok(toLikeablePersons);
        }

        // 나를 좋아요 한 상대들 확인
    @GetMapping("/{userId}/blindDate/fromLike")
    public ResponseEntity<List<FromLikeablePersonResponseDto>> getFromLikeablePersons(@PathVariable String userId, @RequestHeader(value="Authorization")String token){

        Claims claims = jwtProvider.parseJwtToken(token);

        if(!userId.equals(claims.getSubject())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }
        List<FromLikeablePersonResponseDto> fromLikeablePersons = userService.getFromLikeablePersons(userId);
        return ResponseEntity.ok(fromLikeablePersons);
    }

    @GetMapping("/{userId}/finalMatches")
    public ResponseEntity<List<FinalMatchResponseDto>> getFinalMatches(@PathVariable String userId, @RequestHeader(value="Authorization")String token){
        Claims claims = jwtProvider.parseJwtToken(token);


        if(!userId.equals(claims.getSubject())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }


        List<FinalMatchResponseDto> finalMatchedUsers = finalMatchService.getFinalMatchedUser(userId);
        return ResponseEntity.ok(finalMatchedUsers);
    }


    @PostMapping("/blindDate/fromLike/like")
    public ResponseEntity<String> like(@RequestBody LikeRequestDto likeRequest,@RequestHeader(value="Authorization")String token)
    {
        String userId = likeRequest.getUserId(); // 사용자 id
        String targetUserId = likeRequest.getTargetUserId(); // 좋아요를 받은 사용자 id

        Claims claims = jwtProvider.parseJwtToken(token);
        if(!userId.equals(claims.getSubject())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }

        likeablePersonService.saveLike(userId,targetUserId);

        String responseMessage = "두근거렸습니다";
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/blindDate/finalMatches/getExternalId")
    public ResponseEntity<String> getKakaoId(@RequestBody ExternalIdRequestDto externalRequest, @RequestHeader(value="Authorization")String token){
        String userId =externalRequest.getUserId(); // 사용자 Id
        String targetUserId = externalRequest.getTargetUserId();

        Claims claims = jwtProvider.parseJwtToken(token);
        if(!userId.equals(claims.getSubject())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }

        // 해당 targetUserId를 가진 유저의 externalId 가져오기
        String finalMatchExternalId = userService.findExternalId(targetUserId);
        return ResponseEntity.ok(finalMatchExternalId);
    }


    @GetMapping("/group/{userId}/my-rooms")
    public ResponseEntity<?> getHostMeetingRooms(@PathVariable String userId, @RequestHeader(value = "Authorization") String token) {

        Claims claims = jwtProvider.parseJwtToken(token);

        // userId가 본인일 겨우에만 조회 가능하도록 검증
        if (!userId.equals(claims.getSubject())) {
            String responseMessage = "다른 사용자의 미팅방을 조회할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

        List<GroupBlindRoom> meetingRooms = userService.getHostMeetingRooms(userId);
        List<GroupBlindDto> roomDto = meetingRooms.stream()
                .map(GroupBlindDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(roomDto);
    }

    @GetMapping("/mypage/group/{userId}/entering")
    public ResponseEntity<?> getEnteringMeetingRooms(@PathVariable String userId, @RequestHeader(value = "Authorization") String token) {
        Claims claims = jwtProvider.parseJwtToken(token);

        // userId가 본인일 겨우에만 해당 방 정보를 조회할 수 있도록 검증
        if (!userId.equals(claims.getSubject())) {
            String responseMessage = "미팅방 정보를 조회할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

        List<GroupBlindRoom> meetingRooms = userService.getEnteringMeetingRooms(userId);
        List<GroupBlindDto> roomDto = meetingRooms.stream()
                .map(GroupBlindDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(roomDto);
    }

    @GetMapping("/group/{userId}/entering")
    public ResponseEntity<?> enteringMeetingRooms(@PathVariable String userId, @RequestHeader(value = "Authorization") String token) {
        Claims claims = jwtProvider.parseJwtToken(token);

        // userId가 본인일 경우에만 해당 미팅방 정보를 조회할 수 있도록 검증
        if (!userId.equals(claims.getSubject())) {
            String responseMessage = "해당 유저의 미팅방 정보를 조회할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

        List<GroupBlindRoom> meetingRooms = userService.getEnteringMeetingRooms(userId);
        List<GroupBlindDto> roomDto = meetingRooms.stream()
                .map(GroupBlindDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(roomDto);
    }


    @GetMapping("/group/{userId}/achieve")
    public ResponseEntity<?> achievedMeetingRooms(@PathVariable String userId, @RequestHeader(value = "Authorization") String token) {
        Claims claims = jwtProvider.parseJwtToken(token);

        // Check if the requested user is the same as the authenticated user
        if (!userId.equals(claims.getSubject())) {
            String responseMessage = "해당 유저의 성사된 미팅방을 조회할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

        List<GroupBlindRoom> achievedRooms = userService.getAchievedMeetingRooms(userId);
        List<GroupBlindDto> roomDto = achievedRooms.stream()
                .map(GroupBlindDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roomDto);
    }
}




//    @GetMapping("group/{roomId}/entering")
//    public ResponseEntity<?> getEnteringRoomInfo(@PathVariable Integer roomId, @RequestHeader(value="Authorization") String token) {
//        Claims claims = jwtProvider.parseJwtToken(token);
//        GroupBlindRoom groupBlindRoom = groupBlindService.getGroupBlindRoom(roomId);
//
//        if (groupBlindRoom == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Participant userParticipant = groupBlindRoom.getParticipants().stream()
//                .filter(p -> p.getUser().getUserId().equals(claims.getSubject()))
//                .findFirst()
//                .orElse(null);
//
//        if (userParticipant == null) {
//            String responseMessage = "미팅방에 참여한 유저가 아닙니다";
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
//        }
//
//        List<Participant> participants = groupBlindRoom.getParticipants();
//        List<UserInfoDto> members = participants.stream()
//                .map(participant -> {
//                    User user = participant.getUser();
//                    return new UserInfoDto(user.getAge(), user.getDetailProfile().getDepartment(), user.getGender());
//                })
//                .collect(Collectors.toList());
//
//        GroupInfoResponseDto responseDto = new GroupInfoResponseDto(
//                members,
//                groupBlindRoom.getPresentMale(),
//                groupBlindRoom.getPresentFemale(),
//                groupBlindRoom.getGroupBlindIntroduction(),
//                groupBlindRoom.getHostId(),
//                groupBlindRoom.getTitle()
//        );
//        return ResponseEntity.ok(responseDto);
//    }