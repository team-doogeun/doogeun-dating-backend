package com.project.dugeun.domain.user.domain.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum CharacterType {
    CHIC("시크"),
    SWEET("다정"),
    LIVELY("활발"),
    INSENSITIVE("무던"),
    SENSITIVE("세심");


    @Getter
    private final String value;

    CharacterType(String value){
        this.value = value;
    }

    @JsonCreator
    public static CharacterType from(String value){
        for(CharacterType characterType: CharacterType.values()){
            if(characterType.getValue().equals(value)){
                return characterType;
            }
        }
        return null;
    }


    @JsonValue
    public String getValue(){
        return value;
    }
    }
