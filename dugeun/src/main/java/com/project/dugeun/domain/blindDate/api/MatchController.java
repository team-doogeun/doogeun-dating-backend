package com.project.dugeun.domain.blindDate.api;

import com.project.dugeun.domain.blindDate.application.MatchMakerService;
import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.blindDate.dto.MatchResponseDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@Slf4j
@RequiredArgsConstructor
public class MatchController {

    private final MatchMakerService matchMakerService;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    private final JwtProvider jwtProvider;

    // get a list of matches for a given use

    @GetMapping("/blindDate/{userId}/matches")
    public ResponseEntity<MatchResponseDto> getMatches(@PathVariable String userId, @RequestHeader(value = "Authorization") String token) {
        Claims claims = jwtProvider.parseJwtToken(token);

<<<<<<< HEAD
        // userId와 넘겨받은 token의 subject(userId)와 같으면 인증완료
        if(!userId.equals(claims.getSubject())){
            String responseMessage = "해당하는 소개 상대를 확인할 수 없습니다";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
=======
        // 응답 헤더에 클레임 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", claims.getSubject());

        // userId와 넘겨받은 token의 subject(userId)와 같으면 인증완료
        if (!userId.equals(claims.getSubject())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
>>>>>>> 6c7f031913d891aa59c6586cc081bcd15f8b4415
        }

        User user = userRepository.findByUserId(userId);
        List<Match> pair = matchRepository.findByUser1(user);

        if (pair.size() < 2) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        User matchedUser1 = pair.get(0).getUser2();
        User matchedUser2 = pair.get(1).getUser2();
        MatchResponseDto matchResponseDto = new MatchResponseDto(matchedUser1, matchedUser2);

        return ResponseEntity.ok()
                .headers(headers)
                .body(matchResponseDto);
    }

}