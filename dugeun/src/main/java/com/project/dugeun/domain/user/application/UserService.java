package com.project.dugeun.domain.user.application;

import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.GroupBlindStatus;
import com.project.dugeun.domain.likeablePerson.dao.LikeablePersonRepository;
import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.likeablePerson.dto.FromLikeablePersonResponseDto;
import com.project.dugeun.domain.likeablePerson.dto.ToLikeablePersonResponseDto;
import com.project.dugeun.domain.user.domain.profile.UserStatus;
import com.project.dugeun.domain.user.dto.UserResponseDto;
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
    private final LikeablePersonRepository likeablePersonRepository;
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
        return userRepository.findByUserId(userId);
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

    public String checkUserState(String userId){
        User user = userRepository.findByUserId(userId);
        if(user.getUserStatus().equals(UserStatus.COMPLETED)){
            return "completed";
        } else if (user.getUserStatus().equals(UserStatus.PROGRESS)) {
            return "progress";

        }
        return null;
    }

    @Transactional
    public void deleteUser(String userId){

        // 사용자와 관련된 모든 LikeablePerson 레코드 가져오기
        List<LikeablePerson> likeablePersonsToDelete = likeablePersonRepository.findByFromUserUserIdOrToUserUserId(userId, userId);

        // 각 LikeablePerson 레코드 삭제
        for (LikeablePerson likeablePerson : likeablePersonsToDelete) {
            likeablePersonRepository.delete(likeablePerson);
        }

        userRepository.deleteByUserId(userId);

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

    public User findUserByEmailMethod(String email) {
      return userRepository.findByEmail(email);
    }

    public UserResponseDto getUserInfo(String userId) {
        User user = userRepository.findByUserId(userId);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setName(user.getName());
        userResponseDto.setDescription(user.getDescription());
        userResponseDto.setExternalId(user.getExternalId());
        userResponseDto.setUniName(user.getUniName());
        userResponseDto.setStudentId(user.getStudentId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setAge(user.getAge());
        userResponseDto.setGender(user.getGender());
        userResponseDto.setDetailProfile(user.getDetailProfile());
        userResponseDto.setIdealTypeProfile(user.getIdealTypeProfile());

        return userResponseDto;

    }
}