package com.project.dugeun.response;

import java.util.List;

public class ListResponse<T> extends CommonResponse {

    // 공통속성 +  엔티티 T의 리스트 데이터
    List<T> dataList;
}
