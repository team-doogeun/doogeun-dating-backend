package com.project.dugeun.domain.groupblind.application;

import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.GroupBlindStatus;
import com.project.dugeun.domain.groupblind.domain.Participant;
import com.project.dugeun.domain.groupblind.dto.GroupBlindDto;
import com.project.dugeun.domain.groupblind.dto.GroupInfoResponseDto;
import com.project.dugeun.domain.groupblind.dto.UserInfoDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import lombok.RequiredArgsConstructor;
import com.project.dugeun.domain.groupblind.dto.RoomSaveRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GroupBlindService {

    private final GroupBlindRepository groupBlindRepository;
    private final UserRepository userRepository;

    public Integer randomRoomId() {
        Integer rand = (int) (Math.random() * 10000);
        Integer roomId = null;
        GroupBlindRoom randomRoomId = groupBlindRepository.findByRoomId(rand);

        while (randomRoomId != null) {
            roomId = (int) (Math.random() * 10000);
        }
        roomId = rand;
        return roomId;
    }

    @Transactional
    public GroupBlindRoom createMeetingRoom(RoomSaveRequestDto room, String hostUserId) {
        GroupBlindRoom existingRoom = groupBlindRepository.findByTitle(room.getTitle());
        if (existingRoom != null) {
            throw new IllegalStateException("중복된 제목을 가진 미팅방이 있습니다.. ");
        }

        User host = userRepository.findByUserId(hostUserId);
        if (host == null) {
            throw new IllegalStateException("호스트 유저를 찾을 수 없습니다.");
        }


        GroupBlindRoom groupBlindRoom = GroupBlindRoom.builder()
                .roomId(randomRoomId())
                .title(room.getTitle())
                .capacityMale(room.getCapacityMale())
                .capacityFemale(room.getCapacityFemale())
                .groupBlindIntroduction(room.getGroupBlindIntroduction())
                .groupBlindStatus(GroupBlindStatus.NOT_DONE)
                .hostId(hostUserId)
                .build();

        // 방을 만든 사람을 HOST로 지정하여 participant에 추가
        Participant hostParticipant = Participant.builder()
                .user(host)
                .groupBlindRoom(groupBlindRoom)
                .build();

        groupBlindRoom.addHost(hostParticipant);
        if (host.getGender().getValue().equals("남")) {
            groupBlindRoom.setPresentMale(groupBlindRoom.getPresentMale() + 1);
        } else if (host.getGender().getValue().equals("여")) {
            groupBlindRoom.setPresentFemale(groupBlindRoom.getPresentFemale() + 1);
        }
        return groupBlindRepository.save(groupBlindRoom);
    }

    @Transactional
    public boolean deleteMeetingRoom(Integer roomId) {
        GroupBlindRoom groupBlindRoom = groupBlindRepository.findByRoomId(roomId);

        // Delete the meeting room
        groupBlindRepository.delete(groupBlindRoom);

        return true;
    }

    public boolean isHostOfMeetingRoom(Integer roomId, String userId) {
        GroupBlindRoom groupBlindRoom = groupBlindRepository.findByRoomId(roomId);
        return groupBlindRoom != null && groupBlindRoom.getHostId().equals(userId);
    }

    @Transactional
    public ResponseEntity<String> enter(GroupBlindRoom meetingRoom, User user) {

        if (user.getGender().getValue().equals("남")) {
            meetingRoom.setPresentMale(meetingRoom.getPresentMale() + 1);
        } else if (user.getGender().getValue().equals("여")) {
            meetingRoom.setPresentFemale(meetingRoom.getPresentFemale() + 1);
        }

        // Create a participant for the user and add it to the meeting room
        Participant participant = Participant.builder()
                .user(user)
                .groupBlindRoom(meetingRoom)
                .build();

        meetingRoom.addGuest(participant);

        // Save the updated meeting room
        groupBlindRepository.save(meetingRoom);
        String responseMessage = "미팅방 입장에 성공했습니다.";
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseMessage);
    }

    @Transactional
    public void exit(GroupBlindRoom meetingRoom, Participant participant){
        User user = participant.getUser();

        if (user.getGender().getValue().equals("남")) {
            meetingRoom.setPresentMale(meetingRoom.getPresentMale() - 1);
        } else if (user.getGender().getValue().equals("여")) {
            meetingRoom.setPresentFemale(meetingRoom.getPresentFemale() - 1);
        }

        meetingRoom.getParticipants().remove(participant);
        groupBlindRepository.save(meetingRoom);
    }

    @Transactional(readOnly = true)
    public List<GroupBlindDto> getAllMeetingRooms() {
        List<GroupBlindRoom> meetingRooms = groupBlindRepository.findAll();
        return meetingRooms.stream()
                .map(GroupBlindDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GroupBlindRoom getRoomByRoomId(Integer roomId) {
        return groupBlindRepository.findByRoomId(roomId);
    }

    @Transactional(readOnly = true)
    public GroupInfoResponseDto getGroupInfo(Integer roomId) {
        GroupBlindRoom groupBlindRoom = groupBlindRepository.findByRoomId(roomId);

        if (groupBlindRoom == null) {
            throw new IllegalStateException("미팅방을 찾을 수 없습니다.");
        }
        List<Participant> participants = groupBlindRoom.getParticipants();
        List<UserInfoDto> members = participants.stream()
                .map(participant -> {
                    User user = participant.getUser();
                    return new UserInfoDto(user.getUserId(), user.getAge(), user.getDetailProfile().getDepartment(), user.getGender());
                })
                .collect(Collectors.toList());

        return new GroupInfoResponseDto(
                members,
                groupBlindRoom.getRoomId(),
                groupBlindRoom.getPresentMale(),
                groupBlindRoom.getPresentFemale(),
                groupBlindRoom.getGroupBlindIntroduction(),
                groupBlindRoom.getHostId(),
                groupBlindRoom.getTitle()
        );
    }

    @Transactional
    public List<Map<String, String>> startMeeting(Integer roomId, String userId) {
        GroupBlindRoom groupBlindRoom = groupBlindRepository.findByRoomId(roomId);
        groupBlindRoom.setGroupBlindStatus(GroupBlindStatus.DONE);

        if (!groupBlindRoom.getHostId().equals(userId)) {
            throw new IllegalStateException("미팅을 시작할 권한이 없습니다.");
        }

        if (groupBlindRoom.getCapacityMale() != groupBlindRoom.getPresentMale() ||
                groupBlindRoom.getCapacityFemale() != groupBlindRoom.getPresentFemale()) {
            throw new IllegalStateException("아직 충분한 참여자가 모이지 않았습니다.");
        }

        return groupBlindRoom.getParticipants().stream()
                .map(p -> {
                    Map<String, String> participantMap = new HashMap<>();
                    participantMap.put("userId", p.getUser().getUserId());
                    participantMap.put("externalId", p.getUser().getExternalId());
                    return participantMap;
                })
                .collect(Collectors.toList());
    }
}
