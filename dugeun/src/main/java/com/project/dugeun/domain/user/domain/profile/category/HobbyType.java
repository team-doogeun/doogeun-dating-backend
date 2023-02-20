package com.project.dugeun.domain.user.domain.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum HobbyType {

    TRAVEL("여행"),
    HEALTH("헬스"),
    CLIMB("등산"),
    SOCCER("축구"),
    VOLLEY_BALL("배구"),
    BASKET_BALL("농구"),
    BASE_BALL("야구"),
    BIKE("자전거"),
    RUNNING("달리기"),
    MARATHON("마라톤"),
    UDO("유도"),
    TAEKON("태권도"),
    BOXING("복싱"),
    DANCE("댄스");





    @Getter
    private final String value;

    HobbyType(String value){
        this.value = value;
    }

    @JsonCreator
    public static HobbyType from(String value){
        for(HobbyType hobbyType: HobbyType.values()){
            if(hobbyType.getValue().equals(value)){
                return hobbyType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }

    }
