package com.project.dugeun.domain.blindDate.application;

import ch.qos.logback.core.joran.spi.ElementSelector;

public class MatchingScoreStrategyFactory {
    public MatchingScoreStrategy getStrategy(String strategyType){
        if(strategyType.equals("age")){
            return new AgeScoreStrategy();
        } else if (strategyType.equals("address")) {
            return new AddressScoreStrategy();
        } else if (strategyType.equals("body")) {
            return new BodyScoreStrategy();
        } else if(strategyType.equals("character")){
            return new CharacterScoreStrategy();
        } else if(strategyType.equals("department")){
            return new DepartmentScoreStrategy();
        } else if(strategyType.equals("drink")){
            return new DrinkScoreStrategy();
        } else if(strategyType.equals("emotion")){
            return new EmotionScoreStrategy();
        } else if(strategyType.equals("height")){
            return new HeightScoreStrategy();
        } else if(strategyType.equals("hobby")){
            return new HobbyScoreStrategy();
        } else if(strategyType.equals("mbti")){
            return new MbtiScoreStrategy();
        } else if(strategyType.equals("smoke")){
            return new SmokeScoreStrategy();
        }
        return null;
        }
}
