package com.project.dugeun.domain.likeablePerson.application;

import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeablePersonService {


    private final UserRepository userRepository;

   private final FinalMatchRepository finalMatchRepository;


   @Transactional
    public void saveLike(String userId, String targetUserId) {

        FinalMatch actualMatch = new FinalMatch();

        User user1 = userRepository.findByUserId(userId);
        User user2 = userRepository.findByUserId(targetUserId);

        if(user1!=null && user2!= null){
            actualMatch.setUser(user1,user2);
            actualMatch.setCreateDate(LocalDateTime.now());
            finalMatchRepository.save(actualMatch);
        }
        else{

            throw new IllegalStateException("id에 해당하는 회원이 없습니다.");
        }




    }

}
