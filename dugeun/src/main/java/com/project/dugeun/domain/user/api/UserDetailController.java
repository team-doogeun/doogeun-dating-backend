package com.project.dugeun.domain.user.api;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserDetailController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @GetMapping("/userDetail")
    public ResponseEntity<DetailResponseDto> showDetail(@RequestBody DetailRequestDto detailRequestDto, @RequestHeader(value="Authorization")String token){
        String requestUserId = detailRequestDto.getRequestUserId();
        String targetUserId = detailRequestDto.getTargetUserId();
        Claims claims = jwtProvider.parseJwtToken(token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", claims.getSubject());

        if (!requestUserId.equals(claims.getSubject())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        User user = userService.findUserByUserId(targetUserId);
        DetailResponseDto detailResponseDto = new DetailResponseDto(user);

        return ResponseEntity.ok()
                .headers(headers)
                .body(detailResponseDto);

    }


}
