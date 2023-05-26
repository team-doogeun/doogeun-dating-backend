package com.project.dugeun.domain.groupblind.application;

import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import org.springframework.stereotype.Service;

@Service
public class GroupBlindService {

//    @Autowired
    private final GroupBlindRepository groupBlindRepository;

    public GroupBlindService(GroupBlindRepository groupBlindRepository) {
        this.groupBlindRepository = groupBlindRepository;
    }

    // 해당 미팅방 룸의 인원수를 증가시키고
    public void enter(GroupBlindRoom blindRoom, User user){

    if(user.getGender().equals("남")){

        blindRoom.setPresentMale(blindRoom.getPresentMale() + 1);

    } else if (user.getGender().equals("여")) {
        blindRoom.setPresentFemale(blindRoom.getPresentFemale() + 1);

    }


    }

    public void exit(GroupBlindRoom blindRoom, User user){

        if(user.getGender().equals("남")){

            blindRoom.setPresentMale(blindRoom.getPresentMale() - 1);

        } else if (user.getGender().equals("여")) {
            blindRoom.setPresentFemale(blindRoom.getPresentFemale() - 1);

        }

    }




}