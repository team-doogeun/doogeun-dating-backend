package com.project.dugeun.domain.likeablePerson.application;

import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.likeablePerson.dao.LikeablePersonRepository;
import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
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
   private  final LikeablePersonRepository likeablePersonRepository;


   @Transactional
    public void saveLike(String userId, String targetUserId) {

//        FinalMatch actualMatch = new FinalMatch();
       LikeablePerson likeResult = new LikeablePerson();

        User user1 = userRepository.findByUserId(userId);
        User user2 = userRepository.findByUserId(targetUserId);

        if(user1!=null && user2!= null){


                likeResult.setFromUser(user1);
                likeResult.setToUser(user2);
                likeablePersonRepository.save(likeResult);

        }
        else{

            throw new IllegalStateException("id에 해당하는 회원이 없습니다.");
        }




    }



}
