package com.project.dugeun.model.domain.user.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

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
