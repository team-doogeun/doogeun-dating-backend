package com.project.dugeun.domain.user.application;

import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
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
    public List<ToLikeablePersonResponseDto> getToLikeablePersons(String userId) {
        User user = userRepository.findByUserId(userId);

        List<ToLikeablePersonResponseDto> toLikeablePersons = new ArrayList<>();
        for (LikeablePerson likeablePerson : user.getToLikeablePerson())
        {
            // 호감을 표시한 상대의 정보를 LikeablePersonDto로 변환하여 리스트로 추가합니다.
            ToLikeablePersonResponseDto toLikeablePersonResponseDto = ToLikeablePersonResponseDto.fromEntity(likeablePerson);
            toLikeablePersons.add(toLikeablePersonResponseDto);

        }
        return toLikeablePersons;
    }

}