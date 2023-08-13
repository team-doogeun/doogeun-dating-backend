package com.project.dugeun.domain.user.domain.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum DepartmentType {


    ENGINEERING("공학계열"), EDUCATION("교육계열"), SOCIAL_SCIENCE("사회계열"), LIBERAL("인문계열"), SCIENCE("자연계열"),
     MEDICAL("의약계열"), ART("예체능계열");

    @Getter
    private final String value;

    DepartmentType(String value){
        this.value = value;
    }

    @JsonCreator
    public static DepartmentType from(String value){
        for(DepartmentType departmentType: DepartmentType.values()){
            if(departmentType.getValue().equals(value)){
                return departmentType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }

    }
