package com.project.dugeun.entity.user.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum DepartmentType {


    ENGINEERING("공과대학"), EDUCATION("사범대학"), SOCIAL_SCIENCE("사회과학대학"), BUSINESS("경영대학"), LIBERAL("문과대학"), SCIENCE("이과대학"), ARCHITECTURE("건축대학"),
    REAL_ESTATE("부동산과학원"), KU_SCIENCE("KU융합과학기술원"), BIOLOGY("상허생명과학대학"), VETERINARY("수의과대학"), ART("예술디자인대학");

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
