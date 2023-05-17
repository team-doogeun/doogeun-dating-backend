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



    public boolean canMatch(User user1,User user2){

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


            // 점수가 100점 이상이면
            // TODO - 이전에 미지 저장된 Match에 대해서는 저장하지 않아야 함
            if(compatibilityScore >= 50 && !canMatch(user,otherUser)){

                matches.add(otherUser);

                // match(blind_date 디비)에 저장
                saveMatch(user,otherUser, compatibilityScore);


            }
        }


    }


    @Transactional
    public List<User> getMatch(String userId){
        List<User> pair = new ArrayList<>();
        User user = userRepository.findByUserId(userId);
        manageMatches(user);
        List<Match> matches = matchRepository.findByUser1(user);



        // 일단 가장 점수가 높은 순으로 소개해주기 (나중에 이 부분은 변경 가능)
//         matches = (List<Match>) matches.stream().sorted(Comparator.comparing(Match::getCompatibilityScore));


        if (matches.size()>=2 && matches.get(0).getMatched()!=true && matches.get(1).getMatched()!=true) {
            User matchedUser = matches.get(0).getUser2();
            matches.get(0).setMatched(true); // 소개되면 matched를 true로
            User matchedUser2 = matches.get(1).getUser2();
            matches.get(1).setMatched(true); // 소개되면 matched를 true로
            pair.add(matchedUser);
            pair.add(matchedUser2);

            return pair;

        }
        else if (matches.size()==1 && matches.get(0).getMatched()!= true) {
            User matchedUser = matches.get(0).getUser2();
            matches.get(0).setMatched(true); // 소개되면 matched를 true로
            pair.add(matchedUser);

            return pair;
        }


        return new ArrayList<>();
    }




    // TODO - 점수 계산 로직 작성할 것.
    // return a score




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