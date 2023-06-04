package com.project.dugeun.domain.groupblind.application;

import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRole;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.Participant;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import com.project.dugeun.domain.groupblind.dto.RoomSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupBlindService {

    private final GroupBlindRepository groupBlindRepository;
    private final UserRepository userRepository;

    public Integer randomRoomId() {

        Integer rand = (int)(Math.random() * 10000);
        Integer roomId = null;
        GroupBlindRoom randomRoomId = groupBlindRepository.findByRoomId(rand);

        while (randomRoomId != null) {
            roomId = (int)(Math.random() * 10000);
        }
        roomId = rand;
        return roomId;
    }

    @Transactional
    public GroupBlindRoom createMeetingRoom(RoomSaveRequestDto room,  String hostUserId) {
        if(groupBlindRepository.findByTitle(room.getTitle())!=null){
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
                .startTime(LocalDateTime.now())
                .build();
        // 방을 만든 사람을 HOST로 지정하여 participant에 추가
        Participant hostParticipant = Participant.builder()
                .user(host)
                .groupBlindRoom(groupBlindRoom)
                .groupBlindRole(GroupBlindRole.HOST)
                .build();

        if (groupBlindRoom.getParticipants() == null) {
            groupBlindRoom.addHost(hostParticipant);
        }
        return groupBlindRepository.save(groupBlindRoom);

//        return groupBlindRepository.save(GroupBlindRoom.builder()
//                .roomId(room.getRoomId())
//                .title(room.getTitle())
//                .capacityMale(room.getCapacityMale())
//                .capacityFemale(room.getCapacityFemale())
//                .groupBlindIntroduction(room.getGroupBlindIntroduction())
//                .groupBlindStatus(room.getStatus())
//                .build()
//        );
    }

    @Transactional
    public boolean deleteMeetingRoom(Integer roomId, String hostUserId) {
        // Find the meeting room by ID
        // Find the meeting room by roomId
        Optional<GroupBlindRoom> groupBlindRoom = Optional.ofNullable(groupBlindRepository.findByRoomId(roomId));

        if (groupBlindRoom.isPresent()) {
            GroupBlindRoom meetingRoom = groupBlindRoom.get();

            // Check if the user is the host of the meeting room
            boolean isHost = meetingRoom.getParticipants().stream()
                    .anyMatch(p -> p.getGroupBlindRole() == GroupBlindRole.HOST && p.getUser().getUserId().equals(hostUserId));

            if (isHost) {
                // Delete the meeting room
                groupBlindRepository.delete(meetingRoom);
                return true;
            }
        }
        return false;
    }

    @Transactional
    // 해당 미팅방 룸의 인원수를 증가시키고
    public void enter(GroupBlindRoom blindRoom, User user){

        if(user.getGender().equals("남")){

            blindRoom.setPresentMale(blindRoom.getPresentMale() + 1);

        } else if (user.getGender().equals("여")) {
            blindRoom.setPresentFemale(blindRoom.getPresentFemale() + 1);
        }
        Participant guest = Participant.builder()
                .user(user)
                .groupBlindRoom(blindRoom)
                .groupBlindRole(GroupBlindRole.GUEST)
                .build();

        blindRoom.addGuest(guest);
    }

    @Transactional
    public void exit(GroupBlindRoom blindRoom, User user){

        if(user.getGender().equals("남")){

            blindRoom.setPresentMale(blindRoom.getPresentMale() - 1);

        } else if (user.getGender().equals("여")) {
            blindRoom.setPresentFemale(blindRoom.getPresentFemale() - 1);
        }

        blindRoom.getParticipants().removeIf(p -> p.getUser().equals(user));
    }
}