package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.likeablePerson.application.LikeablePersonService;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchMaker {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final LikeablePersonService likeablePersonService;

    public boolean checkMatch(User user1,User user2){

        return matchRepository.existsByUser1AndUser2(user1,user2);
    }

    // find matches for a given user
    @Transactional
    public void manageMatches(User user){

        // 주어진 유저를 제외한 모두 유저들 불러오기
        List<User> users = userRepository.findAllByUserIdNot(user.getUserId());
        List<User> matches = new ArrayList<>();

        for(User otherUser: users){
            int compatibilityScore = ScoreCalculator.calculateCompatibility(user,otherUser);

            // 유저간 점수를 비교하는 알고리즘 작성  ( 점수 높은 최종 2명 )

            System.out.println(compatibilityScore);

            // TODO - 점수가 몇 점 이상부터 저장되도록 구현
            // TODO - 이전에 이미 저장된 Match에 대해서는 저장하지 않아야 함
            if(compatibilityScore >= 50 && !checkMatch(user,otherUser)){
                matches.add(otherUser);
                // match(blind_date 디비)에 저장
                saveMatch(user,otherUser, compatibilityScore);
            }
        }

        List<Match> matchList = matchRepository.findByUser1(user);
        System.out.println(matchList);

        //  소개해줄려는 상대가 2명을 넘지 않으면 임의로 한명 소개해주는 로직
        if(matchList.size() == 1){

            User randomUser = userRepository.findRandomUser();
            matches.add(randomUser);
            saveMatch(user,randomUser,0); // compatibility 점수는 일단 0점으로 부여
        }


        if(matchList.size() == 0)
        {
            User firstRandomUser = userRepository.findRandomUserByGenderNot(user.getGender());
            User secondRandomUser = userRepository.findRandomUserByGenderNot(user.getGender());
            matches.add(firstRandomUser);
            matches.add(secondRandomUser);
            saveMatch(user,firstRandomUser,0);
            saveMatch(user,secondRandomUser,0);
        }
    }

    // save a match between two users
    @Transactional
    private void saveMatch(User user1, User user2, int compatibilityScore){
        Match match = new Match();
        match.setUser1(user1);
        match.setUser2(user2);
        match.setCompatibilityScore(compatibilityScore);
        match.setMatched(false);

        // 양방향 매핑
//        user1.getMatchings().add(match);

        // 양방향 매칭
        user1.addToMatchings(match);
        user2.addToMatchings(match);


        matchRepository.save(match);
        List<Match> allMatches = matchRepository.findAll();
        for(Match selected: allMatches){

            System.out.println("COMPATIBILITY SCORE!!");
            System.out.println(selected.getUser1().getUserId() + " AND " + selected.getUser2().getUserId());

            System.out.println(selected.getCompatibilityScore());

        }

        System.out.println(allMatches);
    }

}