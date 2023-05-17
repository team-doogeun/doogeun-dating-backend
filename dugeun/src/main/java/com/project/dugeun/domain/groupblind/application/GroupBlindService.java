package com.project.dugeun.domain.groupblind.application;

import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import org.springframework.stereotype.Service;

@Service
public class GroupBlindService {

//    @Autowired
    private final GroupBlindRepository groupBlindRepository;

    public GroupBlindService(GroupBlindRepository groupBlindRepository) {
        this.groupBlindRepository = groupBlindRepository;
    }

//    public GroupBlindRoom createGroupBlind(int capacity, GenderType genderType) {
//        GroupBlindRoom groupBlindRoom = GroupBlindRepository.findByCapacityAndGenderType(capacity, genderType);
//    }
}