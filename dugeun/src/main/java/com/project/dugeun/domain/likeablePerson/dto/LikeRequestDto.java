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


    //  Setter 및 생성자 생략 . 기본 생성자만(직렬화를 위한)


    // 클라이언트로부터 전달된 요청 데이터를 처리하는 메서드
    public void valiedateAndProcess(){

        // 요청 데이터 유효성 검증
        if(userId == null || targetUserId == null){
            throw new IllegalArgumentException("사용자 id와 대상 사용자 id가 필요합니다");
        }
    }
    //  요청 데이터 가공 또는 추가 처리
    // 필요한 경우 요청 데이터를 가공하거나 추가적인 작업을 수행할 수 있습니다.
    // 예를 들어, 데이터베이스에 저장하기 위한 엔티티 객체로 변환하는 작업 등을 수행할 수 있습니다.


}
