package com.project.dugeun.domain.likeablePerson.application;

import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.likeablePerson.dao.LikeablePersonRepository;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
       LikeablePerson existingLike = likeablePersonRepository.findByFromUserAndToUser(user1, user2);
       if(existingLike != null){
           return;
       }


       LikeablePerson likeResult = new LikeablePerson();
       likeResult.setFromUser(user1);
       likeResult.setToUser(user2);
       likeablePersonRepository.save(likeResult);
}

    public boolean verifyRelationship(User user1, User user2) {
        LikeablePerson likeablePerson = likeablePersonRepository.findByFromUserAndToUser(user1, user2);

        if (likeablePerson == null) {
            throw new IllegalStateException("관계가 없습니다.");
        }
        return true;
    }

}
