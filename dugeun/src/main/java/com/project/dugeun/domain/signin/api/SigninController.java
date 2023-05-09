package com.project.dugeun.domain.signin.api;

import com.project.dugeun.config.DoogeunUserDetails;
import com.project.dugeun.domain.signin.dto.UserSigninRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class SigninController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserSigninRequestDto loginRequest) {
        try {
            DoogeunUserDetails userDetails = (DoogeunUserDetails) userDetailsService.loadUserByUsername(loginRequest.getUserId());
            if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUserId(), userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token);
                return ResponseEntity.ok().body("로그인에 성공하였습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다.");
            }
        } catch (UsernameNotFoundException e) {
            log.error("User not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자가 존재하지 않습니다.");
        }
    }

//    private final AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserSigninRequestDto loginRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword())
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return ResponseEntity.ok().body("로그인에 성공하였습니다.");
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다.");
//        }
//    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().body("로그아웃에 성공하였습니다.");
    }
}

//    @PostMapping("/login")
//    pu blic ResponseEntity<UserSigninRequestDto> login(@RequestBody UserSigninRequestDto loginRequest) {
//        User user = signinService.loginByLoginRequest(loginRequest);
//        setAuthentication(account);
//        return ResponseEntity.ok(accountMapper.accountToLoginResponse(user));
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<Object> logout(HttpSession session) {
//        session.invalidate();
//        return ResponseEntity.ok(null);
//    }