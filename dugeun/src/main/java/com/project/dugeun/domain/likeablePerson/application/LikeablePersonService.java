package com.project.dugeun.domain.likeablePerson.application;

import com.project.dugeun.domain.likeablePerson.dao.LikeablePersonRepository;
import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
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

    // 이 메서드를 호출하여 targetUserId가 requestUserId를 두근거린 유저인지 검증하고 에러 처리할 수 있습니다.
    public boolean verifyRelationship(User user1, User user2) {
        // requestUser와 targetUser를 이용하여 LikeablePerson 엔티티를 검색합니다.
        LikeablePerson likeablePerson = likeablePersonRepository.findByFromUserAndToUser(user1, user2);

        if (likeablePerson == null) {
            // 관계가 없는 경우 예외 처리를 수행합니다.
            throw new IllegalStateException("관계가 없습니다.");
        }

        // 여기에서 관계가 확인되었으므로 원하는 작업을 수행할 수 있습니다.
        return true;
    }

}
