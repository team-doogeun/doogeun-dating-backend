package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.user.domain.User;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class HobbyScoreStrategy implements MatchingScoreStrategy{

    @Autowired
    private PriorityCalculator priorityCalculator;

    @Override
    public int calculateScore(User user1, User user2){

        ArrayList<String> idealHobbies = new ArrayList<>();
        ArrayList<String> targetHobbies = new ArrayList<>();
        int subScore = 0 ;
        boolean flag = false;

        // 취미 계산 로직
        targetHobbies.add(user2.getDetailProfile().getFirstHobby().getValue());
        targetHobbies.add(user2.getDetailProfile().getSecondHobby().getValue());

        idealHobbies.add(user1.getIdealTypeProfile().getFirstIdealHobby().getValue());
        idealHobbies.add(user1.getIdealTypeProfile().getSecondIdealHobby().getValue());

        for(int i=0; i< targetHobbies.size(); i++){
            if(targetHobbies.contains(idealHobbies.get(i))){

                subScore += 10;
                // 취미가 2개나 똑같더라도 해당 함수는 1번만 적용되도록
                if(!flag){
                    subScore += priorityCalculator.calculatePriorityScore(user1, "취미");
                    flag = true;
                }
            }
        }
        return subScore;
    }
}