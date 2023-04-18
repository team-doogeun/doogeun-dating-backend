package com.project.dugeun.domain.likeablePerson.api;

import com.project.dugeun.domain.likeablePerson.application.LikeablePersonService;
import com.project.dugeun.domain.likeablePerson.dto.LikeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeablePersonController {

    private final LikeablePersonService likeablePersonService;

    // "두근"버튼을 누렀을 때
    @PostMapping("blindDate/like")
    public ResponseEntity like(@RequestBody LikeRequestDto likeRequest){
        // 좋아요 버튼 눌렀을 때의 처리 로직

        // 좋아요 데이터 처리
        String userId = likeRequest.getUserId(); // 사용자 id
        String targetUserId = likeRequest.getTargetUserId(); // 좋아요를 받은 사용자 id


        // 데이터 베이스에 좋아요 정보 저장 또는 업데이트 
    }
}
