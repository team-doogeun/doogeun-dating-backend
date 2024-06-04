package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DepartmentScoreStrategy implements MatchingScoreStrategy{

    PriorityCalculator priorityCalculator = new PriorityCalculator();

    @Override
    public int calculateScore(User user1, User user2){
        int subScore = 0;
        if (Objects.equals(user1.getIdealTypeProfile().getIdealDepartment().getValue(), user2.getDetailProfile().getDepartment().getValue())) {

            subScore += 10;
            subScore += priorityCalculator.calculatePriorityScore(user1, "학과");

        }
        return subScore;
    }
}
