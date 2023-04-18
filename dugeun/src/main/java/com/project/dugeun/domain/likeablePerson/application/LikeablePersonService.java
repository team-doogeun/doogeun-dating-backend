package com.project.dugeun.domain.likeablePerson.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.likeablePerson.dao.LikeablePersonRepository;
import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeablePersonService {

    @Autowired
    private UserRepository userRepository;
   @Autowired
   private FinalMatchRepository finalMatchRepository;


//    private void like(String userId){
//     User fromuser = userRepository.findByUserId(userId);
//     List<LikeablePerson> likeablePeople = likeablePersonRepository.findByFromUser(fromuser);
//
//
//     // LikeablePerson엔티티의 fromUser에 해당 userId의 User등록, toUser에는 좋다고 한 유저 등록
//
//
//     // 해당 유저가 ㅍ포함되어 있는 likeablePeople 리스트
//     for(LikeablePerson like: likeablePeople){
//        User toUser = like.getToUser();
//
//        // 쌍방 두근거려서 매칭 성공 시 true 리턴
//         boolean result = toUser.getToLikeablePerson().stream().anyMatch(e -> e.getToUser().getUserId() == userId);
//
//
//
//     }
//
//    }

    public void saveLike(String userId, String targetUserId) {


        FinalMatch actualMatch = new FinalMatch();
        actualMatch.setUser(userRepository.findByUserId(userId),userRepository.findByUserId(targetUserId));
        finalMatchRepository.save(actualMatch);



    }

}
