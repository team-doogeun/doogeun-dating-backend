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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SigninController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "http://www.localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserSigninRequestDto loginRequest, HttpServletRequest request) {
        try {
            DoogeunUserDetails userDetails = (DoogeunUserDetails) userDetailsService.loadUserByUsername(loginRequest.getUserId());
            if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUserId(), userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token);

                // 세션 ID를 생성하고, 클라이언트에 반환함
                HttpSession session = request.getSession();
                String sessionId = session.getId();
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("sessionId", sessionId);
                responseBody.put("userId", loginRequest.getUserId());
                responseBody.put("name", userDetails.getName());
                return ResponseEntity.ok().body(responseBody);

//                return ResponseEntity.ok().body(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다.");
            }
        } catch (UsernameNotFoundException e) {
            log.error("User not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자가 존재하지 않습니다.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        } else {
            return ResponseEntity.ok().body("세션이 만료되었습니다.");
        }
        return ResponseEntity.ok().body("로그아웃에 성공하였습니다.");
    }
}