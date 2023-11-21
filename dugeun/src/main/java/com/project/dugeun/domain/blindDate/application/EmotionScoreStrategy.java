package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class EmotionScoreStrategy implements MatchingScoreStrategy{

    @Autowired
    private PriorityCalculator priorityCalculator;


    @Override
    public int calculateScore(User user1, User user2){
        // 성격 계산 로직
        int subScore = 0;
        if(Objects.equals(user1.getIdealTypeProfile().getSecondIdealCharacter().getValue(), user2.getDetailProfile().getSecondCharacter().getValue())) {

            subScore += 10;
            subScore += priorityCalculator.calculatePriorityScore(user1, "성격");

        }
        return subScore;
    }
}