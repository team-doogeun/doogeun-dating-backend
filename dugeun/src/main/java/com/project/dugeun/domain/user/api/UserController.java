package com.project.dugeun.domain.user.api;

import com.project.dugeun.domain.finalMatch.application.FinalMatchService;
import com.project.dugeun.domain.likeablePerson.application.LikeablePersonService;
import com.project.dugeun.domain.likeablePerson.dto.LikeRequestDto;
import com.project.dugeun.domain.user.application.UserService;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.dto.*;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final FinalMatchService finalMatchService;
    private final LikeablePersonService likeablePersonService;
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

    @GetMapping("/{userId}/finalMatches")
    public ResponseEntity<List<FinalMatchResponseDto>> getFinalMatches(@PathVariable String userId, @RequestHeader(value="Authorization")String token){
        Claims claims = jwtProvider.parseJwtToken(token);


        if(!userId.equals(claims.getSubject())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }


        List<FinalMatchResponseDto> finalMatchedUsers = finalMatchService.getFinalMatchedUser(userId);
        return ResponseEntity.ok(finalMatchedUsers);
    }


    @PostMapping("/blindDate/fromLike/like")
    public ResponseEntity<String> like(@RequestBody LikeRequestDto likeRequest,@RequestHeader(value="Authorization")String token)
    {
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

    @GetMapping("/blindDate/finalMatches/getExternalId")
    public ResponseEntity<String> getKakaoId(@RequestBody ExternalIdRequestDto externalRequest, @RequestHeader(value="Authorization")String token){
        String userId =externalRequest.getUserId(); // 사용자 Id
        String targetUserId = externalRequest.getTargetUserId();

        Claims claims = jwtProvider.parseJwtToken(token);
        if(!userId.equals(claims.getSubject())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }

        // 해당 targetUserId를 가진 유저의 externalId 가져오기
        String finalMatchExternalId = userService.findExternalId(targetUserId);
        return ResponseEntity.ok(finalMatchExternalId);


    }


}
