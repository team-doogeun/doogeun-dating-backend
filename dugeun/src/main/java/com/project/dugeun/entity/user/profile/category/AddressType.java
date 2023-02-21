package com.project.dugeun.entity.user.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum AddressType {
    GANGNAM("강남구"), GANGDONG("강동구"), GANGBOOK("강북구"), GANGSEO("강서구"), GWANAK("관악구"), GWANGJIN("광진구"), GURO("구로구"), GEUMCHUNG("금천구"), NOAWAN("노원구"),
    DOBONG("도봉구"), DONGDAEMOON("동대문구"), DONGJAK("동작구"), MAPO("마포구"), SEODAEMOON("서대문구"), SEOCHO("서초구"), SEONGDONG("성동구"), SEONGBOOK("성북구"), SONGPA("송파구"),
    YANGCHEUN("양천구"), YOUNGDEUNGPO("영등포구"), YONGSAN("용산구"), EUNPEUNG("은평구"), JONGRO("종로구"), JUNGOO("중구"), JUNGNANG("중량구"), GYEONGGI("경기"), INCHEON("인천");



    @Getter
    private final String value;

    AddressType(String value){this.value = value;}


    @JsonCreator
    public static AddressType from(String value){
        for(AddressType addressType: AddressType.values()){
            if(addressType.getValue().equals(value)){
                return addressType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue(){
        return value;
    }

    }
