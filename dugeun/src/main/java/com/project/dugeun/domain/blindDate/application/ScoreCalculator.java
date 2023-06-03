package com.project.dugeun.domain.blindDate.application;


import com.project.dugeun.domain.user.domain.User;

import java.util.ArrayList;

public class ScoreCalculator {


    public static int calculateCompatibility(User user1, User user2){

        int score = 0 ;
        // 학과, 나이  , 주소, 취미 ...
        String firstPri = user1.getDetailProfile().getFirstPriority().getValue();
        String secondPri = user1.getDetailProfile().getSecondPriority().getValue();
        String thirdPri = user1.getDetailProfile().getThirdPriority().getValue();

        ArrayList<String> idealHobbies = new ArrayList<>();
        ArrayList<String> targetHobbies = new ArrayList<>();

        // ** 점수 내는 로직 **
        // 다른 성별 소개 못하도록 조치
        if(user1.getGender().getValue() == user2.getGender().getValue())
        {
            score -= 100;
        }
        // 나이
        String idealAgeType = user1.getIdealTypeProfile().getIdealAge().getValue();
        Integer targetedAge = user2.getAge();
        String targetedAgeType = "";

        if(targetedAge <= 23){
            targetedAgeType = "20대 초반";
        }
        else if(targetedAge>=24 && targetedAge <27){
            targetedAgeType = "20대 중반";
        }
        else if(targetedAge>= 27 && targetedAge<30){
            targetedAgeType = "20대 후반";
        }
        else if(targetedAge >=30){
            targetedAgeType = "30대 초반";
        }

        if(targetedAgeType == idealAgeType){
            score += 10;

            if(user1.getDetailProfile().getFirstPriority().getValue() == "나이"){
                score += 20;
            }
            else if(user1.getDetailProfile().getSecondPriority().getValue() == "나이"){
                score += 10;
            }
            else if(user1.getDetailProfile().getThirdPriority().getValue() == "나이"){
                score += 5;
            }


        }

        // 학과
        if(user1.getIdealTypeProfile().getIdealDepartment().getValue() == user2.getDetailProfile().getDepartment().getValue()){
            score += 10;

            // 우선순위에 해당 카테고리가 있으면 추가 점수
            if(user1.getDetailProfile().getFirstPriority().getValue() == "학과"){
                score += 20;
            }

            else if(user1.getDetailProfile().getSecondPriority().getValue() == "학과"){
                score += 10;
            }

            else if(user1.getDetailProfile().getThirdPriority().getValue() == "학과"){
                score += 5;
            }
        }


        // 주소 ( 주소지가 가까우면 점수 부여 )
        if(user1.getDetailProfile().getAddress().getValue() == user2.getDetailProfile().getAddress().getValue()){
            score+=10;
            // 우선순위에 해당 카테고리가 있으면 추가 점수
            if(user1.getDetailProfile().getFirstPriority().getValue() == "주소"){
                score += 20;
            }

            else if(user1.getDetailProfile().getSecondPriority().getValue() == "주소"){
                score += 10;
            }

            else if(user1.getDetailProfile().getThirdPriority().getValue() == "주소"){
                score += 5;
            }

        }



        // 취미
        targetHobbies.add(user2.getDetailProfile().getHobby1().getValue());
        targetHobbies.add(user2.getDetailProfile().getHobby2().getValue());

        idealHobbies.add(user1.getIdealTypeProfile().getIdealHobby1().getValue());
        idealHobbies.add(user1.getIdealTypeProfile().getIdealHobby2().getValue());

        // 15점씩
        for(int i=0; i<2; i++){
            if(targetHobbies.contains(idealHobbies.get(i))){

                score += 15;
                if(user1.getDetailProfile().getFirstPriority().getValue() == "취미"){
                    score += 20;

                }else if(user1.getDetailProfile().getSecondPriority().getValue() == "취미"){
                    score += 10;
                }
                else if(user1.getDetailProfile().getThirdPriority().getValue() == "취미"){
                    score += 5;
                }
            }

        }



        // 성격
        if(user1.getIdealTypeProfile().getIdealCharacter1().getValue() == user2.getDetailProfile().getCharacter1().getValue()){
            score+=10;
            if(user1.getDetailProfile().getFirstPriority().getValue() == "성격"){
                score += 20;

            }else if(user1.getDetailProfile().getSecondPriority().getValue() == "성격"){
                score += 10;
            }
            else if(user1.getDetailProfile().getThirdPriority().getValue() == "성격"){
                score += 5;
            }

        }

        if(user1.getIdealTypeProfile().getIdealCharacter2().getValue()== user2.getDetailProfile().getCharacter2().getValue()){
            score+=10;
            if(user1.getDetailProfile().getFirstPriority().getValue() == "성격"){
                score += 20;

            }else if(user1.getDetailProfile().getSecondPriority().getValue() == "성격"){
                score += 10;
            }
            else if(user1.getDetailProfile().getThirdPriority().getValue() == "성격"){
                score += 5;
            }
        }


        // 키
        String idealHeightTYpe = user1.getIdealTypeProfile().getIdealHeight().getValue();
        Integer targetedHeight = user2.getDetailProfile().getHeight();
        String targetHeightType ="";

        if(targetedHeight <= 150){
            targetHeightType = "150 이하";
        }
        else if(150<= targetedHeight && targetedHeight< 155){
            targetHeightType = "150이상 155미만";
        }
        else if(155<= targetedHeight && targetedHeight< 160){
            targetHeightType = "155이상 160미만";
        }
        else if(160<= targetedHeight && targetedHeight<165){
            targetHeightType = "160이상 165미만";
        }
        else if(165<= targetedHeight && targetedHeight<170){
            targetHeightType = "165이상 170미만";
        }
        else if(170<= targetedHeight && targetedHeight<175){
            targetHeightType = "170이상 175미만";
        }
        else if(175<= targetedHeight && targetedHeight<180){
            targetHeightType = "175이상 180미만";
        }
        else if(180<= targetedHeight && targetedHeight<185){
            targetHeightType = "180이상 185미만";
        }
        else if(185<= targetedHeight && targetedHeight<190){
            targetHeightType = "185이상 190미만";
        }
        else if(targetedHeight >= 190){
            targetHeightType = "190 이상";
        }

        if(targetHeightType == idealHeightTYpe){
            score+=10;
            if(user1.getDetailProfile().getFirstPriority().getValue() == "키"){
                score += 20;

            }else if(user1.getDetailProfile().getSecondPriority().getValue() == "키"){
                score += 10;
            }
            else if(user1.getDetailProfile().getThirdPriority().getValue() == "키"){
                score += 5;
            }
        }

        // 체형
        if(user1.getIdealTypeProfile().getIdealBodyType().getValue() == user2.getDetailProfile().getBodyType().getValue())
        {
            score += 10;
            if(user1.getDetailProfile().getFirstPriority().getValue() == "체형"){
                score += 20;

            }else if(user1.getDetailProfile().getSecondPriority().getValue() == "체형"){
                score += 10;
            }
            else if(user1.getDetailProfile().getThirdPriority().getValue() == "체형"){
                score += 5;
            }
        }


        // 흡연 정도
        if(user1.getIdealTypeProfile().getIdealSmoke().getValue() == user2.getDetailProfile().getSmoke().getValue())
        {
            score += 10;
            if(user1.getDetailProfile().getFirstPriority().getValue() == "흡연 정도"){
                score += 20;

            }else if(user1.getDetailProfile().getSecondPriority().getValue() == "흡연 정도"){
                score += 10;
            }
            else if(user1.getDetailProfile().getThirdPriority().getValue() == "흡연 정도"){
                score += 5;
            }

        }

        // 음주 정도
        if(user1.getIdealTypeProfile().getIdealSmoke().getValue() == user2.getDetailProfile().getSmoke().getValue())
        {
            score += 10;
            if(user1.getDetailProfile().getFirstPriority().getValue() == "음주 정도"){
                score += 20;

            }else if(user1.getDetailProfile().getSecondPriority().getValue() == "음주 정도"){
                score += 10;
            }
            else if(user1.getDetailProfile().getThirdPriority().getValue() == "음주 정도"){
                score += 5;
            }
        }



        if(user1.getIdealTypeProfile().getIdealMbti().getValue() == user2.getDetailProfile().getMbti().getValue()){
            score += 10;
            if(user1.getDetailProfile().getFirstPriority().getValue() == "MBTI"){
                score += 20;

            }else if(user1.getDetailProfile().getSecondPriority().getValue() == "MBTI"){
                score += 10;
            }
            else if(user1.getDetailProfile().getThirdPriority().getValue() == "MBTI"){
                score += 5;
            }
        }

        return score;

    }
}