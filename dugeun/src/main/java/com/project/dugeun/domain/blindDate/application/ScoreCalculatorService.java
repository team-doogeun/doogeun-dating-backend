package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreCalculatorService {

    @Autowired
    private MatchingScoreStrategyFactory strategyFactory;
    private MatchingScoreStrategy strategy;

    public int calculateCompatibility(User user1, User user2,String trait)
    {
        strategy = strategyFactory.getStrategy(trait);
        return strategy.calculateScore(user1,user2);

    }

 }
