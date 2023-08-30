package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;

import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchService {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final ScoreCalculatorService scoreCalculatorService;

    @Transactional
    public boolean checkMatch(User user1, User user2) {
        return matchRepository.existsByUser1AndUser2(user1, user2) || matchRepository.existsByUser1AndUser2(user2, user1);
    }
    @Transactional
    public void manageMatches(User user) {

        // 주어진 유저를 제외한 모두 유저들 불러오기
        List<User> users = userRepository.findAllByUserIdNot(user.getUserId());

        for (User otherUser : users) {
            int compatibilityScore = scoreCalculatorService.calculateCompatibility(user, otherUser);

            if (compatibilityScore >= 50 && !checkMatch(user, otherUser)) {
                saveMatch(user, otherUser, compatibilityScore);
            }
        }

        List<Match> matchList = matchRepository.findByUser1(user);

        //  기준 점수에 충족되는 상대가 2명이 되지 않으면 임의로 한 명 소개해주는 로직 ( 1명일 때 )
        if (matchList.size() == 1)
        {
            List<User> usersToExclude = new ArrayList<>();
            usersToExclude.add(user);
            User randomUser = findRandomUser(user.getGender(), usersToExclude);
            saveMatch(user, randomUser, 0); // compatibility 점수는 일단 0점으로 부여
        }

        ///  기준 점수에 충족되는 상대가 0명이면 임의로 두 명 소개해주는 로직 ( 0명일 때 )
        if (matchList.isEmpty())
        {
            List<User> usersToExclude = new ArrayList<>();
            usersToExclude.add(user);
            User firstRandomUser = findRandomUser(user.getGender(), usersToExclude);

            usersToExclude.add(firstRandomUser);
            User secondRandomUser = findRandomUser(user.getGender(), usersToExclude);

            saveMatch(user, firstRandomUser, 0);
            saveMatch(user, secondRandomUser, 0);
        }
    }

    private User findRandomUser(GenderType gender, List<User> usersToExclude) {
        List<User> availableUsers = userRepository.findByGenderNotAndUserIdNotIn(gender, usersToExclude.stream().map(User::getUserId).collect(Collectors.toList()));

        if (!availableUsers.isEmpty()) {
            int randomIndex = new Random().nextInt(availableUsers.size());
            return availableUsers.get(randomIndex);
        } else {
            return null;
        }
    }

    @Transactional
    public void saveMatch(User user1, User user2, int compatibilityScore) {
        if (!checkMatch(user1, user2)) {
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

    public List<Match> getMatches(String userId){
        User user = userRepository.findByUserId(userId);
        return matchRepository.findByUser1(user);
    }

}