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
    public void saveFinalMatch(String userId){

        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not found with userId: " + userId);
        }

        List<LikeablePerson> likeablePeople  = likeablePersonRepository.findByFromUser(user);
        for(LikeablePerson pair: likeablePeople){

            User toUser = pair.getToUser();
            List<LikeablePerson> toUserLikeablePeople =  likeablePersonRepository.findByFromUser(toUser);
            if(toUserLikeablePeople == null){
                throw new RuntimeException("LikeablePerson not found with tmUser: " + user);
            }


            if(finalMatchRepository.findByUser1AndUser2(user, toUser) != null || finalMatchRepository.findByUser1AndUser2(toUser, user) != null){
                continue;
            }


            toUserLikeablePeople.stream().forEach(toUserLikeablePerson -> {
                if(toUserLikeablePerson.getToUser().getUserId().equals(userId)){

                    // check if FinalMatch already exists
                    FinalMatch existingMatch = finalMatchRepository.findByUser1AndUser2(user, toUser);
                    Match introduceMatch = matchRepository.findByUser1AndUser2(user,toUser);

                    if(existingMatch == null){
                        // create new FinalMatch and save it
                        FinalMatch finalMatch = new FinalMatch();
                        FinalMatch anotherFinalMatch = new FinalMatch();
                        finalMatch.setUser1(user);
                        finalMatch.setUser2(toUser);
                        anotherFinalMatch.setUser1(toUser);
                        anotherFinalMatch.setUser2(user);
                        finalMatchRepository.save(finalMatch);
                        finalMatchRepository.save(anotherFinalMatch);

                        introduceMatch.setMatched(true);
                    }
                }
            }

            );

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