package com.project.dugeun.domain.blindDate.application;


import com.project.dugeun.domain.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class ScoreCalculatorService {
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

    // 나이 계산 로직
    public int calculateAgeScore(User user1, User user2)
    {
        int subScore = 0;
        if (Objects.equals(user1.getIdealTypeProfile().getIdealDepartment().getValue(), user2.getDetailProfile().getDepartment().getValue())) {

            subScore += 10;
            subScore += calculatePriorityScore(user1, "나이");

        }
        return subScore;
    }


    public int calculateDepartmentScore(User user1, User user2)
    {
        // 학과 계산 로직
        int subScore = 0;
        if (Objects.equals(user1.getIdealTypeProfile().getIdealDepartment().getValue(), user2.getDetailProfile().getDepartment().getValue())) {

            subScore += 10;
            subScore += calculatePriorityScore(user1, "학과");

        }
        return subScore;
    }

    public int calculateAddressScore(User user1, User user2)
    {
        // 주소 계산 로직
        int subScore = 0;
        if(Objects.equals(user1.getDetailProfile().getAddress().getValue(), user2.getDetailProfile().getAddress().getValue())) {

            subScore += 10;
            subScore += calculatePriorityScore(user1, "주소");

        }
        return subScore;
    }



    public int calculateHobbiesScore(User user1, User user2)
    {
        ArrayList<String> idealHobbies = new ArrayList<>();
        ArrayList<String> targetHobbies = new ArrayList<>();
        int subScore = 0 ;

        // 취미 계산 로직
        targetHobbies.add(user2.getDetailProfile().getFirstHobby().getValue());
        targetHobbies.add(user2.getDetailProfile().getSecondHobby().getValue());

        idealHobbies.add(user1.getIdealTypeProfile().getFirstIdealHobby().getValue());
        idealHobbies.add(user1.getIdealTypeProfile().getSecondIdealHobby().getValue());

        for(int i=0; i< targetHobbies.size(); i++){
            if(targetHobbies.contains(idealHobbies.get(i))){

                subScore += 15;
                subScore += calculatePriorityScore(user1, "성격");

            }
        }
        return subScore;
    }


    public int calculateFirstCharacterScore(User user1, User user2)
    {
        // 성격 계산 로직
        int subScore = 0;
        if(Objects.equals(user1.getIdealTypeProfile().getFirstIdealCharacter().getValue(), user2.getDetailProfile().getFirstCharacter().getValue())) {

            subScore += 10;
            subScore += calculatePriorityScore(user1, "성격");

        }
        return subScore;
    }

    
    public int calculateSecCharacterScore(User user1, User user2)
    {
        // 성격 계산 로직
        int subScore = 0;
        if(Objects.equals(user1.getIdealTypeProfile().getSecondIdealCharacter().getValue(), user2.getDetailProfile().getSecondCharacter().getValue())) {

            subScore += 10;
            subScore += calculatePriorityScore(user1, "성격");

        }
        return subScore;
    }

    
    public int calculateHeightScore(User user1, User user2)
    {
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
            subScore += calculatePriorityScore(user1, "키");

        }
        return subScore;

    }
      

    public int calculateBodyTypeScore(User user1, User user2)
    {
        // 체형 계산 로직
        int subScore = 0;
        if (Objects.equals(user1.getIdealTypeProfile().getIdealBodyType().getValue(), user2.getDetailProfile().getBodyType().getValue())) {

            subScore += 10;
            subScore += calculatePriorityScore(user1, "체형");

        }
        return subScore;
    }

    public int calculateSmokeScore(User user1, User user2)
    {
        // 흡연 정도 계산 로직
        int subScore = 0;
        if (Objects.equals(user1.getIdealTypeProfile().getIdealSmoke().getValue(), user2.getDetailProfile().getSmoke().getValue())) {

            subScore += 10;
            subScore += calculatePriorityScore(user1, "흡연 정도");

        }
        return subScore;
    }

    public int calculateDrinkScore(User user1, User user2)
    {
        // 음주 정도 계산 로직
        int subScore = 0;
        if (Objects.equals(user1.getIdealTypeProfile().getIdealDrink().getValue(), user2.getDetailProfile().getDrink().getValue())) {

            subScore += 10;
            subScore += calculatePriorityScore(user1, "음주 정도");

        }
        return subScore;
    }

    public int calculateMbtIScore(User user1, User user2)
    {
        // MBTI 계산 로직
        int subScore = 0;
        if (Objects.equals(user1.getIdealTypeProfile().getIdealMbti().getValue(), user2.getDetailProfile().getMbti().getValue())) {

            subScore += 10;
            subScore += calculatePriorityScore(user1, "MBTI");

        }
        return subScore;

    }

    public int calculateCompatibility(User user1, User user2) {
        int score = 0;
        if(Objects.equals(user1.getGender().getValue(), user2.getGender().getValue()))
            {
                score -= 100;
            }

            score += this.calculateAgeScore(user1,user2);
            score += this.calculateDepartmentScore(user1,user2);
            score += this.calculateAddressScore(user1, user2);
            score += this.calculateHobbiesScore(user1, user2);
            score += this.calculateFirstCharacterScore(user1, user2);
            score += this.calculateSecCharacterScore(user1,user2);
            score += this.calculateHeightScore(user1,user2);
            score += this.calculateBodyTypeScore(user1,user2);
            score += this.calculateSmokeScore(user1,user2);
            score += this.calculateDrinkScore(user1,user2);
            score += this.calculateMbtIScore(user1,user2);

             return score;
        }


 }
