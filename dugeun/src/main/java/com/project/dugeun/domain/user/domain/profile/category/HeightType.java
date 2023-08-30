package com.project.dugeun.domain.user.domain.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum HeightType {
    UNDER150("150 이하"),
    BETWEEN150_155("150이상 155미만"),
    BETWEEN155_160("155이상 160미만"),
    BETWEEN160_165("160이상 165미만"),
    BETWEEN165_170("165이상 170미만"),
    BETWEEN170_175("170이상 175미만"),
    BETWEEN175_180("175이상 180미만"),
    BETWEEN180_185("180이상 185미만"),
    BETWEEN185_190("185이상 190미만"),
    OVER190("190 이상");


    @Getter
    private final String value;

    HeightType(String value){
        this.value = value;
    }

    @JsonCreator
    public static HeightType from(String value){
        for(HeightType heightType: HeightType.values()){
            if(heightType.getValue().equals(value)){
                return heightType;
            }
        }
        return null;
    }
    @JsonValue
    public String getValue(){
        return value;
    }

    }
