package com.project.dugeun.domain.user.domain.profile.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum HobbyType {

    WALKING("걷기"),
    TRAVEL("여행"),
    HEALTH("헬스"),
    CLIMB("등산"),
    SOCCER("축구"),
    VOLLEY_BALL("배구"),
    BASKET_BALL("농구"),
    BASE_BALL("야구"),
    BIKE("자전거"),
    RUNNING("달리기"),
    MARATHON("마라톤"),
    UDO("유도"),
    TAEKON("태권도"),
    BOXING("권투"),
    MUETIE("무에타이"),
    GUMDO("검도"),
    DANCE("댄스"),
    WALK("산책"),
    SWIM("수영"),
    WATERSKI("수상스키"),
    SURFING("서핑"),
    BOARDGAME("보드게임"),
    SKI("스키"),
    TENNIS("테니스"),
    BADMINTEN("배드민턴"),
    YOGA("요가"),
    MAKING("공예"),
    DRAWING("그림"),
    COOKING("요리"),
    SEWING("자수"),
    KNITTING("뜨개질"),
    PHOTO("사진찍기"),
    SEOYEAH("서예"),
    WRITING("글쓰기"),

    FASHION("패션"),
    SHOPPING("쇼핑"),
    GAME("게임"),
    BLOG("블로그"),
    COLLECTING("골동품 수집"),
    EATING("맛집 탐방"),
    MONEY("재테크"),
    VIOLIN("바이올린"),
    GUITAR("기타"),
    DRUM("드럼"),
    PIANO("피아노"),
    COINSING("코인노래방"),
    FOOT("풋살");
    




    @Getter
    private final String value;

    HobbyType(String value){
        this.value = value;
    }

    @JsonCreator
    public static HobbyType from(String value){
        for(HobbyType hobbyType: HobbyType.values()){
            if(hobbyType.getValue().equals(value)){
                return hobbyType;
            }
        }
        return null;
    }



    }
