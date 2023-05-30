package com.project.dugeun.domain.groupblind.application;

import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.dto.RoomSaveRequestDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GroupBlindService {

//    @Autowired
    private final GroupBlindRepository groupBlindRepository;
    private final UserRepository userRepository;




    public GroupBlindService(GroupBlindRepository groupBlindRepository, UserRepository userRepository) {
        this.groupBlindRepository = groupBlindRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    // 해당 미팅방 룸의 인원수를 증가시키고
    public void enter(GroupBlindRoom blindRoom, User user){

    if(user.getGender().equals("남")){

        blindRoom.setPresentMale(blindRoom.getPresentMale() + 1);

    } else if (user.getGender().equals("여")) {
        blindRoom.setPresentFemale(blindRoom.getPresentFemale() + 1);

    }


    }

    @Transactional
    public void exit(GroupBlindRoom blindRoom, User user){

        if(user.getGender().equals("남")){

            blindRoom.setPresentMale(blindRoom.getPresentMale() - 1);

        } else if (user.getGender().equals("여")) {
            blindRoom.setPresentFemale(blindRoom.getPresentFemale() - 1);

        }

    }



    @Transactional
    public GroupBlindRoom createMeetingRoom(RoomSaveRequestDto room) {
       if(groupBlindRepository.findByTitle(room.getTitle())!=null){
           throw new IllegalStateException("중복된 제목을 가진 미팅방이 있습니다.. ");
       }

        System.out.println(room);

        return groupBlindRepository.save(GroupBlindRoom.builder()
                .roomId(room.getRoomId())
                .title(room.getTitle())
                .capacityMale(room.getCapacityMale())
                .capacityFemale(room.getCapacityFemale())
                .groupBlindIntroduction(room.getGroupBlindIntroduction())
                .groupBlindStatus(room.getStatus())
                .build()
        );

    }

//    private String generateRoomId() {
//        return UUID.randomUUID().toString();
//    }
}