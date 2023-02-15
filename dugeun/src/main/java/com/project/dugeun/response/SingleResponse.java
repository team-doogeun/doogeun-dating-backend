package com.project.dugeun.response;

public class SingleResponse<T> extends CommonResponse {
    
    // 공통속성 + 엔티티 T의 단일 데이터
    T data;
}
