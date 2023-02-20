package com.project.dugeun.domain.user.domain.profile.category;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import static java.util.stream.Collectors.toMap;
@Getter
public enum MbtiType {
    INTP("INTP"),
    ISTP("ISTP"),
    INTJ("INTJ"),
    ISTJ("ISTJ"),
    INFJ("INFJ"),
    ISFJ("ISFJ"),
    ISFP("ISFP"),
    INFP("INFP"),
    ENFJ("ENFJ"),
    ENTJ("ENTJ"),
    ESFJ("ESFJ"),
    ESTP("ESTP"),
    ENFP("ENFP");

    @Getter
    private final String value;

    MbtiType(String value){
        this.value = value;
    }

    @JsonCreator
    public static MbtiType from(String value){
        for(MbtiType mbtiType: MbtiType.values()){
            if(mbtiType.getValue().equals(value)){
                return mbtiType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }


}
