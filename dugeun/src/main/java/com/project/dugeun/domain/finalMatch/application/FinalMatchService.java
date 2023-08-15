package com.project.dugeun.domain.finalMatch.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.likeablePerson.dao.LikeablePersonRepository;
import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.finalMatch.dto.FinalMatchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FinalMatchService {

    private final FinalMatchRepository finalMatchRepository;
    private final LikeablePersonRepository likeablePersonRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;


    @Transactional(readOnly = false)
    public void saveFinalMatch(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found with userId: " + userId);
        }

        List<LikeablePerson> likeablePeople = likeablePersonRepository.findByFromUser(user);

        for (LikeablePerson pair : likeablePeople) {
            User toUser = pair.getToUser();

            List<LikeablePerson> toUserLikeablePeople = likeablePersonRepository.findByFromUser(toUser);
            if (toUserLikeablePeople == null) {
                throw new RuntimeException("LikeablePerson not found with toUser: " + toUser);
            }

            boolean userFound = toUserLikeablePeople.stream()
                    .anyMatch(toUserLikeablePerson -> toUserLikeablePerson.getToUser() != null && toUserLikeablePerson.getToUser().getUserId() != null && toUserLikeablePerson.getToUser().getUserId().equals(userId));

            if (userFound) {

                // FinalMatch가 이미 있는 지 확인
                FinalMatch existingMatch1 = finalMatchRepository.findByUser1AndUser2(user, toUser);
                FinalMatch existingMatch2 = finalMatchRepository.findByUser1AndUser2(toUser, user);

                if (existingMatch1 == null && existingMatch2 == null) {

                    FinalMatch finalMatch1 = new FinalMatch();
                    finalMatch1.setUser1(user);
                    finalMatch1.setUser2(toUser);
                    finalMatchRepository.save(finalMatch1);

                    Match introduceMatch = matchRepository.findByUser1AndUser2(user, toUser);
                    Match introduceMatch2 = matchRepository.findByUser1AndUser2(toUser, user);
                    if (introduceMatch != null ) {
                        introduceMatch.setMatched(true);
                        matchRepository.save(introduceMatch);  // 변경된 속성 저장
                    }
                    if (introduceMatch2 != null ) {
                        introduceMatch2.setMatched(true);
                        matchRepository.save(introduceMatch2); // 변경된 속성 저장
                    }
                }
            }
        }
    }

    public List<FinalMatchResponseDto> getFinalMatchedUser(String userId) {

        List<User> matchedUsers = new ArrayList<>();
        User user = userRepository.findByUserId(userId);
        List<FinalMatch> finalMatches = finalMatchRepository.findByUser1(user);
        List<FinalMatchResponseDto> finalMatchResponseDtos = new ArrayList<>();


        for(FinalMatch finalMatch:finalMatches){
            matchedUsers.add(finalMatch.getUser2());
        }

        for(User matchedUser: matchedUsers){
            FinalMatchResponseDto finalMatchResponseDto = FinalMatchResponseDto.fromEntity(matchedUser);
            finalMatchResponseDtos.add(finalMatchResponseDto);
        }

        return finalMatchResponseDtos;
    }
}