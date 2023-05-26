package com.project.dugeun.domain.groupblind.api;


import com.project.dugeun.domain.base.rq.RequestUser;
import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.dto.CreateGroupBlindRequest;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/groubBlind")
@RequiredArgsConstructor
public class GroupBlindController {

    private final RequestUser requestUser;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupBlindRepository groupBlindRepository;

    @PostMapping("/new/{userId}")
    public ResponseEntity<String> createMeetingRoom(@RequestBody CreateGroupBlindRequest request) {
        // 요청 데이터 처리
        String title = request.getTitle();
        int capacity = request.getCapacity();
        GenderType genderType = request.getGenderType();

        // 유효성 검사
        if (title == null || title.isEmpty()) {
            return ResponseEntity.badRequest().body("Meeting room title is required.");
        }

        if (capacity < 2 || capacity % 2 != 0) {
            return ResponseEntity.badRequest().body("Capacity should be an even number greater than or equal to 2.");
        }

        // 미팅방 생성
        GroupBlindRoom meetingRoom = GroupBlindRoom.builder()
                .title(title)
                .capacity(capacity)
                .genderType(genderType)
                .build();

        // 참여자 정보 처리
        com.project.dugeun.domain.user.domain.User host = requestUser.getMember();
        if (host == null) {
            return ResponseEntity.badRequest().body("Host information is missing.");
        }

        meetingRoom.setParticipants(Collections.singletonList(host));

        // 미팅방 저장
        groupBlindRepository.save(meetingRoom);

        return ResponseEntity.ok("Meeting room created successfully.");
    }
}