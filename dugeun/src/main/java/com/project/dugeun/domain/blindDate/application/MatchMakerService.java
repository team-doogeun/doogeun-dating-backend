package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
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
public class MatchMakerService {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final ScoreCalculatorService scoreCalculatorService;

    public boolean checkMatch(User user1,User user2){

        return matchRepository.existsByUser1AndUser2(user1,user2);
    }


    @Transactional
    public void manageMatches(User user){

        // 주어진 유저를 제외한 모두 유저들 불러오기
        List<User> users = userRepository.findAllByUserIdNot(user.getUserId());
        List<User> matches = new ArrayList<>();

        for(User otherUser: users){
            int compatibilityScore = scoreCalculatorService.calculateCompatibility(user,otherUser);

            if(compatibilityScore >= 50 && !checkMatch(user,otherUser)){
                matches.add(otherUser);
                saveMatch(user,otherUser, compatibilityScore);
            }
        }

        List<Match> matchList = matchRepository.findByUser1(user);

        //  기준 점수에 충족되는 상대가 2명이 되지 않으면 임의로 한 명 소개해주는 로직 ( 1명일 때 )
        if(matchList.size() == 1)
        {
            User randomUser = userRepository.findRandomUser();
            matches.add(randomUser);
            saveMatch(user,randomUser,0); // compatibility 점수는 일단 0점으로 부여
        }

        ///  기준 점수에 충족되는 상대가 0명이면 임의로 두 명 소개해주는 로직 ( 0명일 때 )
        if(matchList.isEmpty())
        {
            User firstRandomUser = userRepository.findRandomUserByGenderNot(user.getGender());
            User secondRandomUser = userRepository.findRandomUserByGenderNot(user.getGender());
            matches.add(firstRandomUser);
            matches.add(secondRandomUser);
            saveMatch(user,firstRandomUser,0);
            saveMatch(user,secondRandomUser,0);
        }
    }


    @Transactional
    public void saveMatch(User user1, User user2, int compatibilityScore)
    {
        Match match = new Match();
        match.setUser1(user1);
        match.setUser2(user2);
        match.setCompatibilityScore(compatibilityScore);
        match.setMatched(false);
        user1.addToMatchings(match);
        user2.addToMatchings(match);
        matchRepository.save(match);

    }

}