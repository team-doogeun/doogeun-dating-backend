package com.project.dugeun.domain.finalMatch.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.dateChat.daetChatRoom.dao.DateChatRoomRepository;
import com.project.dugeun.domain.dateChat.daetChatRoom.domain.DateChatRoom;
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
    private final DateChatRoomRepository dateChatRoomRepository;


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

                    FinalMatch finalMatch = new FinalMatch();
                    finalMatch.setUser1(user);
                    finalMatch.setUser2(toUser);
                    finalMatchRepository.save(finalMatch);

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

                    // 최종 매칭이 되면 자동으로 이에 해당되는 대화방이 자동으로 생성됨
                    DateChatRoom dateChatRoom =  DateChatRoom.create(finalMatch);
                    dateChatRoomRepository.save(dateChatRoom);
                    dateChatRoom.addChatUser(finalMatch.getUser1());
                    dateChatRoom.addChatUser(finalMatch.getUser2());

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
            FinalMatchResponseDto finalMatchResponseDto = FinalMatchResponseDto.fromEntity(finalMatch);
            matchedUsers.add(finalMatch.getUser2());
        }


        return finalMatchResponseDtos;
    }
}