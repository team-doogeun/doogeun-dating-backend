package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.user.domain.User;

import java.util.Objects;

public class HeightScoreStrategy implements MatchingScoreStrategy{

    PriorityCalculator priorityCalculator;

    @Override
    public int calculateScore(User user1, User user2){
        int subScore = 0;
        Integer targetedHeight = user2.getDetailProfile().getHeight();
        String targetHeightType = "";
        if (targetedHeight <= 150) {
            targetHeightType = "150 이하";
        } else if (targetedHeight < 155) {
            targetHeightType = "150이상 155미만";
        } else if (targetedHeight < 160) {
            targetHeightType = "155이상 160미만";
        } else if (targetedHeight < 165) {
            targetHeightType = "160이상 165미만";
        } else if (targetedHeight < 170) {
            targetHeightType = "165이상 170미만";
        } else if (targetedHeight < 175) {
            targetHeightType = "170이상 175미만";
        } else if (targetedHeight < 180) {
            targetHeightType = "175이상 180미만";
        } else if (targetedHeight < 185) {
            targetHeightType = "180이상 185미만";
        } else if (targetedHeight < 190) {
            targetHeightType = "185이상 190미만";
        } else {
            targetHeightType = "190 이상";
        }

        if (Objects.equals(user1.getIdealTypeProfile().getIdealHeight().getValue(), targetHeightType))
        {

            subScore += 10;
            subScore += priorityCalculator.calculatePriorityScore(user1, "키");

        }
        return subScore;
    }
}
