package com.project.dugeun.domain.signin.api;

import com.project.dugeun.domain.signin.application.LoginService;
import com.project.dugeun.domain.signin.dto.UserSignInRequestDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    private final LoginService loginService;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    //==토큰 생성 컨트롤러==//
    @PostMapping(value = "/login")
    public TokenResponse<TokenDataResponse> createToken(@RequestBody UserSignInRequestDto loginRequest) throws Exception {

       if(Boolean.TRUE.equals(loginService.login(loginRequest))){

           // == 토근 생성 진행 ==
           String token = jwtProvider.createToken(loginRequest.getUserId()); // 토큰 생성
           Claims claims = jwtProvider.parseJwtToken("Bearer "+ token); // 토큰 검증

           User user= userRepository.findByUserId(claims.getSubject());

           TokenDataResponse tokenDataResponse = new TokenDataResponse(token, user.getName() , claims.getSubject(), claims.getIssuedAt().toString(), claims.getExpiration().toString());
           return new TokenResponse<>("200", "OK", tokenDataResponse);
       }
       return new TokenResponse<>("444","no token", null);
    }

    //==Response DTO==//
    @Data
    @AllArgsConstructor
    static class TokenResponse<T> {

        private String code;
        private String msg;
        private T data;

    }

    //==Response DTO==//
    @Data
    @AllArgsConstructor
    static class TokenResponseNoData {
        private String code;
        private String msg;
    }

    //==Response DTO==//
    @Data
    @AllArgsConstructor
    static class TokenDataResponse {
        private String token;
        private String name;
        private String subject;
        private String issuedTime;
        private String expiredTime;
    }
}