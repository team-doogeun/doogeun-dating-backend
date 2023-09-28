package com.project.dugeun.domain.likeablePerson.api;

import com.project.dugeun.domain.likeablePerson.application.LikeablePersonService;
import com.project.dugeun.domain.likeablePerson.dto.LikeResponseDto;
import com.project.dugeun.domain.likeablePerson.dto.LikeRequestDto;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class LikeablePersonController {
    private final LikeablePersonService likeablePersonService;
    private final JwtProvider jwtProvider;
    // "두근"버튼을 누렀을 때
    @PostMapping("/blindDate/like")
    public ResponseEntity<LikeResponseDto> like(@RequestBody LikeRequestDto likeRequest, @RequestHeader(value="Authorization")String token){
        String userId = likeRequest.getUserId(); // 사용자 id
        String targetUserId = likeRequest.getTargetUserId(); // 좋아요를 받은 사용자 id
        Claims claims = jwtProvider.parseJwtToken(token);
        LikeResponseDto likeResponseDto = new LikeResponseDto();
        if(!userId.equals(claims.getSubject()))
        {
            likeResponseDto.setSuccess(false);
          return ResponseEntity.ok()
                  .body(likeResponseDto);
        }

        likeablePersonService.saveLike(userId,targetUserId);
        likeResponseDto.setSuccess(true);

        return ResponseEntity.ok().body(likeResponseDto);

   }
}
