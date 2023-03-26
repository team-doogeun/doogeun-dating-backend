package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.category.HobbyType;
import com.project.dugeun.domain.user.domain.profile.category.PriorityCategory;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchMaker {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    // find matches for a given user
    public List<User> findMatches(User user){


        // 주어진 유저를 제외한 모두 유저들 불러오기
      List<User> users = userRepository.findAllByUserIdNot(user.getUserId());
      List<User> matches = new ArrayList<>();

      for(User otherUser: users){
          int compatibilityScore = calculateCompatibility(user,otherUser);
          System.out.println(compatibilityScore);
          // 점수가 60점 이상이면
          if(compatibilityScore >= 20){
            matches.add(otherUser);
            saveMatch(user,otherUser, compatibilityScore);
          }
      }
      return matches;
    }


    // TODO - 점수 계산 로직 작성할 것.
    // return a score
    private int calculateCompatibility(User user1, User user2){

        int score = 0 ;
        // 학과, 나이  , 주소, 취미 ...
        String firstPri = user1.getDetailProfile().getFirstPriority().getValue();
        String secondPri = user1.getDetailProfile().getSecondPriority().getValue();
        String thirdPri = user1.getDetailProfile().getThirdPriority().getValue();

        ArrayList<String> idealHobbies = new ArrayList<>();
        ArrayList<String> targetHobbies = new ArrayList<>();

        if(firstPri == "나이"){

        }

        // 이상형의 학과가 같은 결우 점수 부여
        else if(firstPri == "학과"){

            if(user1.getIdealTypeProfile().getIdealDepartment().getValue() == user2.getDetailProfile().getDepartment().getValue()){
                score += 20;
            }
        }

        // 주소지가 가까우면 점수 부여
        else if(firstPri == "주소"){

            if(user1.getDetailProfile().getAddress().getValue() == user2.getDetailProfile().getAddress().getValue()){
                score+=20;
            }



        }
        else if(firstPri == "취미"){
           targetHobbies.add(user2.getDetailProfile().getHobby1().getValue());
           targetHobbies.add(user2.getDetailProfile().getHobby2().getValue());

           idealHobbies.add(user1.getIdealTypeProfile().getIdealHobby1().getValue());
           idealHobbies.add(user1.getIdealTypeProfile().getIdealHobby2().getValue());

           // 15점씩
            for(int i=0; i<2; i++){
               if(targetHobbies.contains(idealHobbies.get(i))){
                   score += 15;
               }

            }

        }
        else if(firstPri == "성격"){
            if(user1.getIdealTypeProfile().getIdealCharacter1().getValue() == user2.getDetailProfile().getCharacter1().getValue()){
                score+=15;
            }
            if(user1.getIdealTypeProfile().getIdealCharacter2().getValue()== user2.getDetailProfile().getCharacter2().getValue()){
                score+=15;
            }

        }

        else if(firstPri == "키"){

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
                score+=20;
            }

        }

        else if(firstPri == "체형"){
                if(user1.getIdealTypeProfile().getIdealBodyType().getValue() == user2.getDetailProfile().getBodyType().getValue())
                {
                    score += 20;
                }

        }
        else if(firstPri == "흡연 정도"){
            if(user1.getIdealTypeProfile().getIdealSmoke().getValue() == user2.getDetailProfile().getSmoke().getValue())
            {
            score+=20;

        }
        else if(firstPri == "음주 정도"){

            if(user1.getIdealTypeProfile().getIdealSmoke().getValue() == user2.getDetailProfile().getSmoke().getValue())
            {
                score += 20;
            }
            }

        }
        else if(firstPri == "MBTI"){
            if(user1.getIdealTypeProfile().getIdealMbti().getValue() == user2.getDetailProfile().getMbti().getValue()){
                score += 20;
            }

        }


        return score;
//        return 70;
    }

    // save a match between two users
    private void saveMatch(User user1, User user2, int compatibilitySocre){
        Match match = new Match();
        match.setUser1(user1);
        match.setUser2(user2);
        match.setCompatibilityScore(compatibilitySocre);
        matchRepository.save(match);

    }
}
