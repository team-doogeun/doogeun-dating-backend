package com.project.dugeun.domain.blindDate.application;

import org.springframework.stereotype.Component;

@Component
public class MatchingScoreStrategyFactory {
    public MatchingScoreStrategy getStrategy(String strategyType){
        if(strategyType.equals("AGE")){
            return new AgeScoreStrategy();
        } else if (strategyType.equals("ADDRESS")) {
            return new AddressScoreStrategy();
        } else if (strategyType.equals("BODY")) {
            return new BodyScoreStrategy();
        } else if(strategyType.equals("CHARACTER")){
            return new CharacterScoreStrategy();
        } else if(strategyType.equals("DEPARTMENT")){
            return new DepartmentScoreStrategy();
        } else if(strategyType.equals("DRINK")){
            return new DrinkScoreStrategy();
        } else if(strategyType.equals("EMOTION")){
            return new EmotionScoreStrategy();
        } else if(strategyType.equals("HEIGHT")){
            return new HeightScoreStrategy();
        } else if(strategyType.equals("HOBBY")){
            return new HobbyScoreStrategy();
        } else if(strategyType.equals("MBTI")){
            return new MbtiScoreStrategy();
        } else if(strategyType.equals("SMOKE")){
            return new SmokeScoreStrategy();
        }
        return null;
        }
}
