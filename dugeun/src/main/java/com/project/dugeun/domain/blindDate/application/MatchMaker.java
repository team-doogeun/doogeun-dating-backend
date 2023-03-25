package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchMaker {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    // find matches for a given user
    public List<User> findMatches(User user){


        // 주어진 유저를 제외한 모두 유저들 불러오기
      List<User> users = userRepository.findAllByUserIdNot(user.getUserId());
      List<User> matches = new ArrayList<>();

      for(User otherUser: users){
          int compatibilityScore = calculateCompatibility(user,otherUser);

          // 점수가 60점 이상이면
          if(compatibilityScore >= 60){
            matches.add(otherUser);
            saveMatch(user,otherUser, compatibilityScore);
          }
      }
      return matches;
    }


    // TODO - 점수 계산 로직 작성할 것.
    private int calculateCompatibility(User user1, User user2){
        return 0;
    }

    // save a match between two users
    private void saveMatch(User user1, User user2, int compatibilitySocre){
        Match match = new Match();
        match.setUser1(user1);
        match.setUser2(user2);
        match.setCompatibilityScore(compatibilitySocre);
        matchRepository.save(match);

    }
}
