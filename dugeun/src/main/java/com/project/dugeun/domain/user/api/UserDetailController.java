package com.project.dugeun.domain.user.api;

import com.project.dugeun.domain.likeablePerson.application.LikeablePersonService;
import com.project.dugeun.domain.user.application.UserService;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.dto.DetailRequestDto;
import com.project.dugeun.domain.user.dto.DetailResponseDto;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserDetailController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final LikeablePersonService likeablePersonService;

    @GetMapping("/likeToMe/userDetail")
    public ResponseEntity<DetailResponseDto> showToMeDetail(@RequestParam("requestUserId")String requestUserId,@RequestParam("targetUserId")String targetUserId, @RequestHeader(value = "Authorization") String token) {

        Claims claims = jwtProvider.parseJwtToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", claims.getSubject());

        if (!requestUserId.equals(claims.getSubject())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        //  targetUserId가 requestUserId를 두근거린 유저인지 검증, 아니면 에러 처리
        try {
            likeablePersonService.verifyRelationship(userService.findUserByUserId(targetUserId), userService.findUserByUserId(requestUserId));
        } catch (IllegalStateException e) {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(null);
        }

        User user = userService.findUserByUserId(targetUserId);
        DetailResponseDto detailResponseDto = new DetailResponseDto(user);

        return ResponseEntity.ok()
                .headers(headers)
                .body(detailResponseDto);

    }

    @GetMapping("/likeFromMe/userDetail")
    public ResponseEntity<DetailResponseDto> showFromMeDetail(@RequestParam("requestUserId")String requestUserId,@RequestParam("targetUserId")String targetUserId,@RequestHeader(value = "Authorization") String token) {

        Claims claims = jwtProvider.parseJwtToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", claims.getSubject());

        if (!requestUserId.equals(claims.getSubject())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        // targetUserId가 requestUserId가두근거린 유저인지 검증, 아니면 에러 처리
        try {
            likeablePersonService.verifyRelationship(userService.findUserByUserId(requestUserId), userService.findUserByUserId(targetUserId));
        } catch (IllegalStateException e) {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(null);
        }

        User user = userService.findUserByUserId(targetUserId);
        DetailResponseDto detailResponseDto = new DetailResponseDto(user);

        return ResponseEntity.ok()
                .headers(headers)
                .body(detailResponseDto);


    }
}