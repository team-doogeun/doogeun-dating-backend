package com.project.dugeun.domain.finalMatch.application;

import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
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

        List<LikeablePerson> likeablePeople  = likeablePersonRepository.findByFromUser(user);
        for(LikeablePerson pair: likeablePeople){

          User toUser = pair.getToUser();
          List<LikeablePerson> toUserLikeablePeople =  likeablePersonRepository.findByFromUser(toUser);

          toUserLikeablePeople.stream().forEach(toUserLikeablePerson -> {
              if(toUserLikeablePerson.getToUser().getUserId() == userId){
                  FinalMatch finalMatch = new FinalMatch();
                  finalMatch.setUser1(user);
                  finalMatch.setUser2(toUser);
                  finalMatchRepository.save(finalMatch);
              }
          });

        }

    }

}
