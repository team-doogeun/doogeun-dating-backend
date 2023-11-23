package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class PriorityCalculator {

    public int calculatePriorityScore(User user1, String targetValue)
    {
        if (user1.getDetailProfile().getFirstPriority().getValue().equals(targetValue)) {
            return 20;
        } else if (user1.getDetailProfile().getSecondPriority().getValue().equals(targetValue)) {
            return 10;
        } else if (user1.getDetailProfile().getThirdPriority().getValue().equals(targetValue)) {
            return 5;
        } else {
            return 0;
        }
    }
}
