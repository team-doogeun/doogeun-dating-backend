package com.project.dugeun.domain.user.domain.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum PriorityCategory {

    AGE("나이"),
    DEPARTMENT("학과"),ADDRESS("주소"),
    HOBBY("취미"),CHARACTER("성격"),HEIGHT("키"),
    BODY("체형"),SMOKE("흡연 정도"),DRINK("음주 정도"),MBTI("MBTI");

    @Getter
    private final String value;

    PriorityCategory(String value){
        this.value = value;
    }

    @JsonCreator
    public static PriorityCategory from(String value){
        for(PriorityCategory priorityCategory: PriorityCategory.values()){
            if(priorityCategory.getValue().equals(value)){
                return priorityCategory;
            }
        }
        return null;
    }


}
