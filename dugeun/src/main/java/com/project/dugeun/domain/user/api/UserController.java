package com.project.dugeun.domain.user.api;

import com.project.dugeun.domain.user.application.UserService;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.dto.ToLikeablePersonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    // 나의 소개팅 조회
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{userId}/blindDate")
    public ResponseEntity<List<ToLikeablePersonResponseDto>> getToLikeablePersons(@PathVariable String userId, Principal principal){

        if(!userId.equals(principal.getName())){
            String responseMessage = "접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 403 Forbidden 상태 반환
        }
            List<ToLikeablePersonResponseDto> toLikeablePersons = userService.getToLikeablePersons(userId);
            return ResponseEntity.ok(toLikeablePersons);
        }
}
