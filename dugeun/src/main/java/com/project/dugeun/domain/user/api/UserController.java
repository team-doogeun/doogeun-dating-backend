package com.project.dugeun.domain.user.api;

import com.project.dugeun.domain.user.application.UserService;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.dto.FromLikeablePersonResponseDto;
import com.project.dugeun.domain.user.dto.ToLikeablePersonResponseDto;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    // 나가 좋아요 한 상대들 확인
    @GetMapping("/{userId}/blindDate/toLike")
    public ResponseEntity<List<ToLikeablePersonResponseDto>> getToLikeablePersons(@PathVariable String userId, @RequestHeader(value="Authorization")String token){

        Claims claims = jwtProvider.parseJwtToken(token);

        if(!userId.equals(claims.getSubject())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }
            List<ToLikeablePersonResponseDto> toLikeablePersons = userService.getToLikeablePersons(userId);
            return ResponseEntity.ok(toLikeablePersons);
        }

        // 나를 좋아요 한 상대들 확인
    @GetMapping("/{userId}/blindDate/fromLike")
    public ResponseEntity<List<FromLikeablePersonResponseDto>> getFromLikeablePersons(@PathVariable String userId, @RequestHeader(value="Authorization")String token){

        Claims claims = jwtProvider.parseJwtToken(token);

        if(!userId.equals(claims.getSubject())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }
        List<FromLikeablePersonResponseDto> fromLikeablePersons = userService.getFromLikeablePersons(userId);
        return ResponseEntity.ok(fromLikeablePersons);
    }
}
