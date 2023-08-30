package com.project.dugeun.domain.likeablePerson.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class LikeRequestDto {
    @NotEmpty
    private String userId; // 사용자 ID
    @NotEmpty
    private String targetUserId; // 좋아요를 받은 대상 사용자 ID

    public void valiedateAndProcess(){

        // 요청 데이터 유효성 검증
        if(userId == null || targetUserId == null){
            throw new IllegalArgumentException("사용자 id와 대상 사용자 id가 필요합니다");
        }
    }

}
