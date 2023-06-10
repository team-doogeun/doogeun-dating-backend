package com.project.dugeun.domain.groupblind.application;

import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRole;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.Participant;
import com.project.dugeun.domain.groupblind.dto.GroupInfoResponseDto;
import com.project.dugeun.domain.groupblind.dto.UserInfoDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import lombok.RequiredArgsConstructor;
import com.project.dugeun.domain.groupblind.dto.RoomSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
                .groupBlindStatus(room.getStatus())
                .groupBlindRole(GroupBlindRole.HOST)
                .hostId(hostUserId)
                .build();

        // 방을 만든 사람을 HOST로 지정하여 participant에 추가
        Participant hostParticipant = Participant.builder()
                .user(host)
                .groupBlindRoom(groupBlindRoom)
//                .groupBlindRole(GroupBlindRole.HOST)
                .build();

        groupBlindRoom.addHost(hostParticipant);
        if (host.getGender().equals(GenderType.MAN)) {
            groupBlindRoom.setPresentMale(groupBlindRoom.getPresentMale() + 1);
        } else if (host.getGender().equals(GenderType.WOMAN)) {
            groupBlindRoom.setPresentFemale(groupBlindRoom.getPresentFemale() + 1);
        }
        return groupBlindRepository.save(groupBlindRoom);
    }

    @Transactional
    public boolean deleteMeetingRoom(Integer roomId, String hostUserId) {

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

    @Transactional(readOnly = true)
    public List<GroupBlindRoom> getAllMeetingRooms() {
        return groupBlindRepository.findAll();
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
                    return new UserInfoDto(user.getAge(), user.getDetailProfile().getDepartment(), user.getGender());
                })
                .collect(Collectors.toList());

        return new GroupInfoResponseDto(
                members,
                groupBlindRoom.getPresentMale(),
                groupBlindRoom.getPresentFemale(),
                groupBlindRoom.getGroupBlindIntroduction(),
                groupBlindRoom.getHostId(),
                groupBlindRoom.getTitle()
        );
    }

    @Transactional
    public List<String> manageMeeting(Integer roomId, String hostUserId) {
        GroupBlindRoom groupBlindRoom = groupBlindRepository.findByRoomId(roomId);
        if (groupBlindRoom == null) {
            throw new IllegalStateException("미팅방을 찾을 수 없습니다.");
        }

        // Get external IDs of all participants
        List<String> externalIds = groupBlindRoom.getParticipants().stream()
                .map(p -> p.getUser().getExternalId())
                .collect(Collectors.toList());

        // If hostUserId is provided, check if the user is the host of the meeting room
        if (hostUserId != null) {
            boolean isHost = groupBlindRoom.getParticipants().stream()
                    .anyMatch(p -> p.getGroupBlindRole() == GroupBlindRole.HOST && p.getUser().getUserId().equals(hostUserId));

            if (!isHost) {
                return null;
            }

            // Exclude the host from the list of participant external IDs
            externalIds.removeIf(externalId -> externalId.equals(hostUserId));
        }

        return externalIds;
    }

}