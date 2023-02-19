package com.project.dugeun.model.domain.user.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import static java.util.stream.Collectors.toMap;

@Getter
public enum BodyType {
    SKINNY("마름"),
    SLIM("슬림"),
    NORMAL("보통"),
    LITTLE_CHUBBY("약간 통통"),
    CHUBBY("통통"),
    MUSCULAR("근육질");

    @Getter
    private final String value;

    BodyType(String value){
        this.value = value;
    }

    @JsonCreator
    public static BodyType from(String value){
        for(BodyType bodyType: BodyType.values()){
            if(bodyType.getValue().equals(value)){
                return bodyType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }



}
