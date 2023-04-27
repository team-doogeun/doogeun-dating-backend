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

   private  final LikeablePersonRepository likeablePersonRepository;


   @Transactional
    public void saveLike(String userId, String targetUserId) {


        User user1 = userRepository.findByUserId(userId);
        User user2 = userRepository.findByUserId(targetUserId);

       if(user1 == null || user2 == null){
           throw new IllegalStateException("id에 해당하는 회원이 없습니다.");
       }

       // 이미 likeablePerson이 있는지 확인
       LikeablePerson existingLike = likeablePersonRepository.findByFromUserAndToUser(user1, user2);
       if(existingLike != null){
           return; // 이미 저장된 데이터라면 더 이상 진행하지 않고 메소드를 종료합니다.
       }

       // likeablePerson 저장
       LikeablePerson likeResult = new LikeablePerson();
       likeResult.setFromUser(user1);
       likeResult.setToUser(user2);
       likeablePersonRepository.save(likeResult);

}
}
