package com.project.dugeun.domain.user.domain.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum DrinkType {

    NONE("음주안함"),
    OFTEN("종종"),
    HEAVY("애주가");

    @Getter
    private final String value;

    DrinkType(String value){
        this.value = value;
    }

    @JsonCreator
    public static DrinkType from(String value){
        for(DrinkType drinkType: DrinkType.values()){
            if(drinkType.getValue().equals(value)){
                return drinkType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }

}
