package com.project.dugeun.domain.blindDate.exception.finalMatch.application;

import com.project.dugeun.domain.blindDate.exception.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.blindDate.exception.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.likeablePerson.dao.LikeablePersonRepository;
import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FinalMatchService {

    private final FinalMatchRepository finalMatchRepository;
    private final LikeablePersonRepository likeablePersonRepository;
    private final UserRepository userRepository;

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


            toUserLikeablePeople.stream().forEach(toUserLikeablePerson -> {
                if(toUserLikeablePerson.getToUser().getUserId().equals(userId)){

                    // check if FinalMatch already exists
                    FinalMatch existingMatch = finalMatchRepository.findByUser1AndUser1(user, toUser);
                    if(existingMatch == null){
                        // create new FinalMatch and save it
                        FinalMatch finalMatch = new FinalMatch();
                        finalMatch.setUser1(user);
                        finalMatch.setUser2(toUser);
                        finalMatchRepository.save(finalMatch);
                    }
                }
            });

        }

    }

}