package com.project.dugeun.domain.user.application;

import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.GroupBlindStatus;
import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.dto.FromLikeablePersonResponseDto;
import com.project.dugeun.domain.user.dto.ToLikeablePersonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final GroupBlindRepository groupBlindRepository;
    public List<ToLikeablePersonResponseDto> getToLikeablePersons(String userId) {
        User user = userRepository.findByUserId(userId);

       List<ToLikeablePersonResponseDto> toLikeablePersons = new ArrayList<>();

       for (LikeablePerson likeablePerson : user.getToLikeablePerson())
       {
            // 내가 호감을 표시한 상대의 정보를 LikeablePersonDto로 변환하여 리스트로 추가합니다.
           ToLikeablePersonResponseDto toLikeablePersonResponseDto = ToLikeablePersonResponseDto.fromEntity(likeablePerson);
        toLikeablePersons.add(toLikeablePersonResponseDto);

       }



        return toLikeablePersons;
    }
    public User findUserByUserId(String userId) {
        User user =  userRepository.findByUserId(userId);
        return user;
    }

    public List<FromLikeablePersonResponseDto> getFromLikeablePersons(String userId) {
        User user = userRepository.findByUserId(userId);

        List<FromLikeablePersonResponseDto> fromLikeablePersons = new ArrayList<>();

        for (LikeablePerson likeablePerson : user.getFromLikeablePerson())
        {
            // 나에게 호감을 표시한 상대의 정보를 LikeablePersonDto로 변환하여 리스트로 추가합니다.
            FromLikeablePersonResponseDto fromLikeablePersonResponseDto = FromLikeablePersonResponseDto.fromEntity(likeablePerson);
            fromLikeablePersons.add(fromLikeablePersonResponseDto);
        }


        return fromLikeablePersons;
    }

    public String findExternalId(String targetUserId) {
       User user =  userRepository.findByUserId(targetUserId);

        return user.getExternalId();
    }


    @Transactional(readOnly = true)
    public List<GroupBlindRoom> getHostMeetingRooms(String hostUserId) {
        return groupBlindRepository.findByHostId(hostUserId);
    }


    @Transactional(readOnly = true)
    public List<GroupBlindRoom> getEnteringMeetingRooms(String userId) {
        return groupBlindRepository.findByHostId(userId);
    }

    @Transactional(readOnly = true)
    public List<GroupBlindRoom> getAchievedMeetingRooms(String userId) {
        return groupBlindRepository.findByHostIdAndGroupBlindStatus(userId, GroupBlindStatus.DONE);
    }
}