package com.project.dugeun.entity.user.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum GenderType {
    MAN("남"),
    WOMAN("여");


    @Getter
    private final String value;

    GenderType(String value){
        this.value = value;
    }

    @JsonCreator
    public static GenderType from(String value){
        for(GenderType genderType: GenderType.values()){
            if(genderType.getValue().equals(value)){
                return genderType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }



}
