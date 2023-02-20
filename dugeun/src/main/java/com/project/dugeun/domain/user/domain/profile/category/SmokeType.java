package com.project.dugeun.domain.user.domain.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum SmokeType {

    NONE("흡연 안 함"),
    OFTEN("종종"),
    HEAVY("애연가");

    @Getter
    private final String value;

    SmokeType(String value){
        this.value = value;
    }

    @JsonCreator
    public static SmokeType from(String value){
        for(SmokeType smokeType: SmokeType.values()){
            if(smokeType.getValue().equals(value)){
                return smokeType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }




    }
