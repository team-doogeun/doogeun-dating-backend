package com.project.dugeun.domain.user.domain.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AgeType {
    /* 이상형 정보 등록 시에만 사용 */
    EARLY_TWENTY("20대 초반"),
    MIDDLE_TWENTY("20대 중반"),
    LATE_TWENTY("20대 후반"),
    EARLY_THIRTY("30대 초반");

    private final String value;

    AgeType(String value){
        this.value = value;
    }

    @JsonCreator
    public static AgeType from(String value){
        for(AgeType ageType: AgeType.values()){
            if(ageType.getValue().equals(value)){
                return ageType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }


    }
