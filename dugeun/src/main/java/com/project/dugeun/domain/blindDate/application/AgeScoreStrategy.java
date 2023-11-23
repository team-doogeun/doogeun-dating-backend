package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AgeScoreStrategy implements MatchingScoreStrategy{

    @Autowired
    private PriorityCalculator priorityCalculator;

    @Override
    public int calculateScore(User user1,User user2){
        int subScore = 0;
        if (Objects.equals(user1.getIdealTypeProfile().getIdealDepartment().getValue(), user2.getDetailProfile().getDepartment().getValue())) {

            subScore += 10;
            subScore += priorityCalculator.calculatePriorityScore(user1, "나이");

        }
        return subScore;
    }
}
