package com.project.dugeun.domain.blindDate.application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MatchingScoreStrategyFactory {

    private final AddressScoreStrategy addressScoreStrategy;
    private final AgeScoreStrategy ageScoreStrategy;
    private final BodyScoreStrategy bodyScoreStrategy;
    private final CharacterScoreStrategy characterScoreStrategy;
    private final DepartmentScoreStrategy departmentScoreStrategy;
    private final DrinkScoreStrategy drinkScoreStrategy;
    private final EmotionScoreStrategy emotionScoreStrategy;
    private final HeightScoreStrategy heightScoreStrategy;
    private final HobbyScoreStrategy hobbyScoreStrategy;
    private final MbtiScoreStrategy mbtiScoreStrategy;
    private final SmokeScoreStrategy smokeScoreStrategy;

    public MatchingScoreStrategy getStrategy(String strategyType){
        if(strategyType.equals("AGE")){
            return ageScoreStrategy;
        } else if (strategyType.equals("ADDRESS")) {
            return addressScoreStrategy;
        } else if (strategyType.equals("BODY")) {
            return bodyScoreStrategy;
        } else if(strategyType.equals("CHARACTER")){
            return characterScoreStrategy;
        } else if(strategyType.equals("DEPARTMENT")){
            return departmentScoreStrategy;
        } else if(strategyType.equals("DRINK")){
            return drinkScoreStrategy;
        } else if(strategyType.equals("EMOTION")){
            return emotionScoreStrategy;
        } else if(strategyType.equals("HEIGHT")){
            return heightScoreStrategy;
        } else if(strategyType.equals("HOBBY")){
            return hobbyScoreStrategy;
        } else if(strategyType.equals("MBTI")){
            return mbtiScoreStrategy;
        } else if(strategyType.equals("SMOKE")){
            return smokeScoreStrategy;
        }
        return null;
        }
}
