package com.project.dugeun.model.domain.user.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum HeightType {
    UNDER150("150 이하"),
    BETWEEN150_155("150~155"),
    BETWEEN155_160("155~160"),
    BETWEEN160_165("160~165"),
    BETWEEN165_170("165~170"),
    BETWEEN170_175("170~175"),
    BETWEEN175_180("175~180"),
    BETWEEN180_185("180~185"),
    BETWEEN185_190("185~190"),
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
