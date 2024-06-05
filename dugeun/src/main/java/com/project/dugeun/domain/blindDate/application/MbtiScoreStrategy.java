package com.project.dugeun.domain.blindDate.application;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class MbtiScoreStrategy implements MatchingScoreStrategy{

    private final PriorityCalculator priorityCalculator;

    @Override
    public int calculateScore(User user1, User user2){
        int subScore = 0;
        if (Objects.equals(user1.getIdealTypeProfile().getIdealMbti().getValue(), user2.getDetailProfile().getMbti().getValue())) {

            subScore += 10;
            subScore += priorityCalculator.calculatePriorityScore(user1, "MBTI");

        }
        return subScore;
    }

}