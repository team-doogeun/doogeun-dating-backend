package com.project.dugeun.domain.blindDate.api;

import com.project.dugeun.domain.blindDate.application.MatchMaker;
import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.blindDate.dao.dto.MatchResponseDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.security.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@RestController
@Slf4j
@RequiredArgsConstructor
public class MatchController {


    private final MatchMaker matchMaker;

    private final UserRepository userRepository;

    private final MatchRepository matchRepository;

    private final JwtProvider jwtProvider;

    // get a list of matches for a given use

    @GetMapping("/blindDate/{userId}/matches")
    public ResponseEntity getMatches(@PathVariable String userId, @RequestHeader(value = "Authorization") String token) {
        Claims claims = jwtProvider.parseJwtToken(token);

        // userId와 넘겨받은 token의 subject(userId)와 같으면 인증완
        if(!userId.equals(claims.getSubject())){
            String responseMessage = "해당하는 소개 상대를 확인할 수 없습니다";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

        User user = userRepository.findByUserId(userId);
        List<Match> pair = new ArrayList<>();
        pair = matchRepository.findByUser1(user);

        EntityModel<MatchResponseDto> twoPersonEntityModel = null;

        User matchedUser = pair.get(0).getUser2();
        User matchedUser2 = pair.get(1).getUser2();
        twoPersonEntityModel = twoPersonEntityModel.of(new MatchResponseDto(matchedUser, matchedUser2));
        return ResponseEntity.ok(twoPersonEntityModel);

    }


}