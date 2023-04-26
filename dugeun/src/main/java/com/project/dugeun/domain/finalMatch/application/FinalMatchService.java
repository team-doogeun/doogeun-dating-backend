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

    @Transactional
    public void saveFinalMatch(String userId, String anotherUserId){

        User user = userRepository.findByUserId(userId);
        User anotherUser = userRepository.findByUserId(anotherUserId);

        // 모든 선호 리스트를 탐색하는 것보다는 빠를 것으로 예상
        List<LikeablePerson> likeablePeople  = likeablePersonRepository.findByFromUser(user);
        for(LikeablePerson pair:likeablePeople){
        if(pair.getFromUser().getUserId() == userId)
        {
            if(pair.getToUser().getUserId() == anotherUserId){
                // 최종매칭완료
                FinalMatch finalMatch = new FinalMatch();
                finalMatch.setUser(pair.getFromUser(),pair.getToUser());
                finalMatchRepository.save(finalMatch);
            }

        }

        else if(pair.getToUser().getUserId() == userId){
            if(pair.getFromUser().getUserId() == anotherUserId){
                // 최종매칭완료
                FinalMatch finalMatch = new FinalMatch();
                finalMatch.setUser(pair.getToUser(),pair.getFromUser());
                finalMatchRepository.save(finalMatch);
            }
        }

        }





    }

}
