package com.project.dugeun.domain.finalMatch.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.chat.dao.ChatRoomJoinRepository;
import com.project.dugeun.domain.chat.dao.ChatRoomRepository;
import com.project.dugeun.domain.chat.domain.ChatRoom;
import com.project.dugeun.domain.chat.domain.ChatRoomJoin;
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
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FinalMatchService {

    private final FinalMatchRepository finalMatchRepository;
    private final LikeablePersonRepository likeablePersonRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomJoinRepository chatRoomJoinRepository;


    @Transactional(readOnly = false)
    public void saveFinalMatch(String userId) {
        User user = userRepository.findByUserId(userId);

        List<LikeablePerson> likeablePeople = likeablePersonRepository.findByFromUser(user);

        for (LikeablePerson pair : likeablePeople) {
            User toUser = pair.getToUser();

            List<LikeablePerson> toUserLikeablePeople = likeablePersonRepository.findByFromUser(toUser);
            if (toUserLikeablePeople == null) {
                throw new IllegalStateException("해당 유저가 두근거린 상대가 없습니다.: " + toUser);
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

                     // 최종 매칭이 되면 자동으로 이에 해당되는 대화방&대화방 참여자가 자동으로 생성됨
                    ChatRoom chatRoom = new ChatRoom();
                    ChatRoomJoin chatRoomJoin = ChatRoomJoin.builder().chatRoom(chatRoom).user(user).build();
                    ChatRoomJoin anotherChatRoomJoin = ChatRoomJoin.builder().chatRoom(chatRoom).user(toUser).build();
                    chatRoom.getChatRoomJoins().add(chatRoomJoin);
                    chatRoom.getChatRoomJoins().add(anotherChatRoomJoin);
                    user.getChatRoomJoins().add(chatRoomJoin);
                    toUser.getChatRoomJoins().add(anotherChatRoomJoin);
                    finalMatch.setChatRoom(chatRoom);
                    chatRoom.setFinalMatch(finalMatch);
                    chatRoomRepository.save(chatRoom);
                    chatRoomJoinRepository.save(chatRoomJoin);
                    finalMatchRepository.save(finalMatch);


                }


            }
            else {
                throw new IllegalStateException("해당 Final Match가 이미 존재합니다.");
            }
        }
    }


    public List<FinalMatchResponseDto> getFinalMatchedUser(String userId) {

        User user = userRepository.findByUserId(userId);
        List<FinalMatch> finalMatches = finalMatchRepository.findByUser1OrUser2(user, user);

        if(finalMatches.isEmpty()){
            throw new IllegalStateException("해당 유저에 해당되는 최종 매칭이 없습니다.");
        }

        List<FinalMatchResponseDto> finalMatchResponseDtos = new ArrayList<>();

        for (FinalMatch finalMatch : finalMatches) {
            FinalMatchResponseDto finalMatchResponseDto = new FinalMatchResponseDto();

            if (Objects.equals(finalMatch.getUser1().getUserId(), userId)) {
                finalMatchResponseDto.setUserId(finalMatch.getUser2().getUserId());
                finalMatchResponseDto.setId(finalMatch.getId());
                finalMatchResponseDto.setDepartment(finalMatch.getUser2().getDetailProfile().getDepartment());
                finalMatchResponseDto.setAge(finalMatch.getUser2().getAge());
            } else {
                finalMatchResponseDto.setUserId(finalMatch.getUser1().getUserId());
                finalMatchResponseDto.setId(finalMatch.getId());
                finalMatchResponseDto.setDepartment(finalMatch.getUser1().getDetailProfile().getDepartment());
                finalMatchResponseDto.setAge(finalMatch.getUser1().getAge());
            }

            finalMatchResponseDtos.add(finalMatchResponseDto);
        }

        return finalMatchResponseDtos;
    }


}