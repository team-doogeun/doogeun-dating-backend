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


    public boolean checkMatch(User user1, User user2) {
        return matchRepository.existsByUser1AndUser2(user1, user2) || matchRepository.existsByUser1AndUser2(user2, user1);
    }


    @Transactional(readOnly = false)
    public List<Match> calculateMatches(User user){
        List<Match> matchList = new ArrayList<>();
        // 주어진 유저를 제외한 모두 유저들 불러오기
        List<User> users = userRepository.findByGenderNotAndUserIdNot(user.getGender(), user.getUserId());

        for (User otherUser : users) {
            int compatibilityScore = scoreCalculatorService.calculateCompatibility(user, otherUser);

            if (compatibilityScore >= 50 && !checkMatch(user, otherUser)) {
                Match match = new Match();
                match.setUser1(user);
                match.setUser2(otherUser);
                match.setCompatibilityScore(compatibilityScore);
                matchList.add(match);
            }
        }
        return matchList;
    }


    public User findRandomUser(GenderType gender, List<User> usersToExclude) {
        List<User> availableUsers = userRepository.findByGenderNotAndUserIdNotIn(gender, usersToExclude.stream().map(User::getUserId).collect(Collectors.toList()));

        if (!availableUsers.isEmpty()) {
            int randomIndex = new Random().nextInt(availableUsers.size());
            return availableUsers.get(randomIndex);
        } else {
            throw new IllegalStateException("소개 상대가 없습니다.");
        }
    }

    @Transactional
    public void saveMatch(User user1, User user2, int compatibilityScore){
        if (!checkMatch(user1, user2)) {
            Match match = new Match();
            match.setUser1(user1);
            match.setUser2(user2);
            match.setCompatibilityScore(compatibilityScore);
            match.setMatched(false);
            user1.addToMatchings(match);
            user2.addToMatchings(match);
            match.setIntroduced(true);
            matchRepository.save(match);
        }
    }

    public List<Match> getMatches(String userId){
        User user = userRepository.findByUserId(userId);
        List<Match> noFinalMatchedMatches =   filterFinalMatches(matchRepository.findByUser1(user));
        List<Match> noRecentlyIntroducedMatches = filterIntroducedMatches(noFinalMatchedMatches);
        return noRecentlyIntroducedMatches;
    }

    public List<Match> filter(List<Match> matches){
        List<Match> noFinalMatchedMatches =   filterFinalMatches(matches);
        List<Match> noRecentlyIntroducedMatches = filterIntroducedMatches(noFinalMatchedMatches);
        return noRecentlyIntroducedMatches;
    }

    public List<Match> filterFinalMatches(List<Match> matches){
        return matches.stream()
                .filter(match -> !match.isMatched())
                .collect(Collectors.toList());
    }

    public List<Match> filterIntroducedMatches(List<Match> matches){
        return matches.stream()
                .filter(match -> !match.isIntroduced())
                .collect(Collectors.toList());
    }
}