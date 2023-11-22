package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreCalculatorService {

    @Autowired
    private MatchingScoreStrategyFactory strategyFactory;


    public int calculateCompatibility(User user1, User user2)
    {
        MatchingScoreStrategy strategy = strategyFactory.getStrategy("age");
        int ageScore = strategy.calculateScore(user1,user2);
        strategy = strategyFactory.getStrategy("address");
        int addressScore = strategy.calculateScore(user1,user2);
        strategy = strategyFactory.getStrategy("body");
        int bodyScore = strategy.calculateScore(user1,user2);
        strategy = strategyFactory.getStrategy("character");
        int characterScore = strategy.calculateScore(user1,user2);
        strategy = strategyFactory.getStrategy("department");
        int departmentScore = strategy.calculateScore(user1,user2);
        strategy = strategyFactory.getStrategy("drink");
        int drinkScore = strategy.calculateScore(user1,user2);
        strategy = strategyFactory.getStrategy("emotion");
        int emotionScore = strategy.calculateScore(user1,user2);
        strategy = strategyFactory.getStrategy("height");
        int heightScore = strategy.calculateScore(user1,user2);
        strategy = strategyFactory.getStrategy("hobby");
        int hobbyScore = strategy.calculateScore(user1,user2);
        strategy = strategyFactory.getStrategy("mbti");
        int mbtiScore = strategy.calculateScore(user1,user2);
        strategy = strategyFactory.getStrategy("smoke");
        int smokeScore = strategy.calculateScore(user1,user2);
        int totalScore = ageScore + addressScore + hobbyScore + bodyScore + characterScore + departmentScore
                + drinkScore + emotionScore + heightScore + mbtiScore + smokeScore ;
        return totalScore;


    }


 }
