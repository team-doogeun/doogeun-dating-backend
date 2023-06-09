package com.project.dugeun.domain.likeablePerson.api;

import com.project.dugeun.domain.likeablePerson.application.LikeablePersonService;
import com.project.dugeun.domain.likeablePerson.dto.LikeRequestDto;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class LikeablePersonController {

    private final LikeablePersonService likeablePersonService;

    private final JwtProvider jwtProvider;
    // "두근"버튼을 누렀을 때
    @PostMapping("blindDate/like")
    public ResponseEntity<String> like(@RequestBody LikeRequestDto likeRequest,@RequestHeader(value="Authorization")String token){
        String userId = likeRequest.getUserId(); // 사용자 id
        String targetUserId = likeRequest.getTargetUserId(); // 좋아요를 받은 사용자 id

        Claims claims = jwtProvider.parseJwtToken(token);
        if(!userId.equals(claims.getSubject())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }

        likeablePersonService.saveLike(userId,targetUserId);

        String responseMessage = "두근거렸습니다";
        return ResponseEntity.ok(responseMessage);


   }
}
