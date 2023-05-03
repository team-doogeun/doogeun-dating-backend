package com.project.dugeun.domain.user.domain.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum EmotionType {

    RATIONAL("이성적"),
    EMOTIONAL("감성적");

    @Getter
    private final String value;

    EmotionType(String value){
        this.value = value;
    }

    @JsonCreator
    public static EmotionType from(String value){
        for(EmotionType emotionType: EmotionType.values()){
            if(emotionType.getValue().equals(value)){
                return emotionType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }
}
