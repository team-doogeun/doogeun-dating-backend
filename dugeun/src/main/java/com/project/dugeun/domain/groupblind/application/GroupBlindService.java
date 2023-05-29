package com.project.dugeun.domain.groupblind.application;

import com.project.dugeun.domain.base.rq.RequestUser;
import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRole;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.Participant;
import com.project.dugeun.domain.groupblind.dto.CreateGroupBlindRequest;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupBlindService {

    private final GroupBlindRepository meetingRoomRepository;
    private final UserRepository userRepository;
    private final RequestUser requestUser;
//    private final SimpMessagingTemplate messagingTemplate;

    public Long createMeetingRoom(CreateGroupBlindRequest request) {
        // Create a new meeting room object
        GroupBlindRoom meetingRoom = new GroupBlindRoom();
        meetingRoom.setTitle(request.getTitle());
        meetingRoom.setCapacityMale(request.getCapacityMale());
        meetingRoom.setCapacityFemale(request.getCapacityFemale());
        meetingRoom.setGroupBlindIntroduction(request.getGroupBlindIntroduction());

        // Set the host of the meeting room
        com.project.dugeun.domain.user.domain.User host = userRepository.findByUserId(requestUser.getMember().getUserId());
        Participant hostParticipant = new Participant();
        hostParticipant.setUser(host);
        hostParticipant.setGroupBlindRoom(meetingRoom);
        hostParticipant.setGroupBlindRole(GroupBlindRole.HOST);

        // Save the meeting room and host participant
        meetingRoom.getParticipants().add(hostParticipant);
        meetingRoomRepository.save(meetingRoom);

        return meetingRoom.getId();
    }

    public boolean deleteMeetingRoom(Long roomId, User member) {
        // Find the meeting room by ID
        Optional<GroupBlindRoom> meetingRoomOptional = meetingRoomRepository.findById(roomId);

        if (meetingRoomOptional.isPresent()) {
            GroupBlindRoom meetingRoom = meetingRoomOptional.get();

            // Check if the user is the host of the meeting room
//            com.project.dugeun.domain.user.domain.User host = userRepository.findByUserId(requestUser.getMember().getUserId());

            boolean isHost = meetingRoom.getParticipants().stream()
                    .anyMatch(p -> p.getUser().equals(member) && p.getGroupBlindRole() == GroupBlindRole.HOST);

            if (isHost) {
                // Delete the meeting room
                meetingRoomRepository.delete(meetingRoom);
                return true;
            }
        }
        return false;
    }

//    public void startMeetingRoom(Long roomId) {
//        // Find the meeting room by ID
//        Optional<GroupBlindRoom> meetingRoomOptional = meetingRoomRepository.findById(roomId);
//
//        if (meetingRoomOptional.isPresent()) {
//            GroupBlindRoom meetingRoom = meetingRoomOptional.get();
//
//            // Check if the user is the host of the meeting room
//            boolean isHost = meetingRoom.getParticipants().stream()
//                    .anyMatch(p -> p.getGroupBlindRole() == GroupBlindRole.HOST);
//
//            if (isHost) {
//                // Get all the participants in the meeting room
//                List<Participant> participants = meetingRoom.getParticipants();
//
//                // Retrieve the externalIds of all participants
//                List<String> externalIds = participants.stream()
//                        .map(p -> p.getUser().getExternalId())
//                        .collect(Collectors.toList());
//
//                // Send the externalIds to each participant (you can implement the logic for sending externalIds here)
//
//                for (Participant participant : participants) {
//                    String userExternalId = participant.getUser().getExternalId();
//
//                    // Exclude the current participant from the list
//                    List<String> otherExternalIds = new ArrayList<>(externalIds);
//                    otherExternalIds.remove(userExternalId);
//
//                    // Send the otherExternalIds to the current participant
//                    messagingTemplate.convertAndSendToUser(
//                            userExternalId,
//                            "/queue/externalIds",
//                            otherExternalIds
//                    );
//                    // Example: Printing externalIds for demonstration purposes
//                }
//            }
//        }
//    }
}