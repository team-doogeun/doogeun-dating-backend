package com.project.dugeun.domain.signin.api;

import com.project.dugeun.domain.signin.application.LoginService;
import com.project.dugeun.domain.signin.dto.UserSigninRequestDto;
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


    //==토큰 생성 컨트롤러==//
    @PostMapping(value = "/login")
    public TokenResponse createToken(@RequestBody UserSigninRequestDto loginRequest) throws Exception {


       if(loginService.login(loginRequest)){

           // == 토근 생성 진행 ==
           String token = jwtProvider.createToken(loginRequest.getUserId()); // 토큰 생성
           Claims claims = jwtProvider.parseJwtToken("Bearer "+ token); // 토큰 검증

           TokenDataResponse tokenDataResponse = new TokenDataResponse(token, claims.getSubject(), claims.getIssuedAt().toString(), claims.getExpiration().toString());
           TokenResponse tokenResponse = new TokenResponse("200", "OK", tokenDataResponse);


           return tokenResponse;
       }

       return new TokenResponse("444","no token", null);
    }

    //==토큰 인증 컨트롤러==//
    @GetMapping(value = "/checkToken")
    public TokenResponseNoData checkToken(@RequestHeader(value = "Authorization") String token) throws Exception {
        Claims claims = jwtProvider.parseJwtToken(token);

        TokenResponseNoData tokenResponseNoData = new TokenResponseNoData("200", "success");
        return tokenResponseNoData;
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
    static class TokenResponseNoData<T> {

        private String code;
        private String msg;
    }

    //==Response DTO==//
    @Data
    @AllArgsConstructor
    static class TokenDataResponse {
        private String token;
        private String subject;
        private String issued_time;
        private String expired_time;
    }

}