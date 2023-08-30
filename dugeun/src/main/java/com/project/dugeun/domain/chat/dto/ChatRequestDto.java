package com.project.dugeun.domain.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class ChatRequestDto {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String anotherUserId;

    public void valiedateAndProcess(){

        // 요청 데이터 유효성 검증
        if(userId == null || anotherUserId == null){
            throw new IllegalArgumentException("사용자 id와 대상 사용자 id가 필요합니다");
        }
    }
}
