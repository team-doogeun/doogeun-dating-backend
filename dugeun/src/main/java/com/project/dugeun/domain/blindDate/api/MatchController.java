package com.project.dugeun.domain.blindDate.api;

import com.project.dugeun.domain.blindDate.application.MatchMaker;
import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.blindDate.dto.MatchResponseDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@RestController
@Slf4j
public class MatchController {

    @Autowired
    private MatchMaker matchMaker;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    private  org.springframework.security.core.userdetails.User user;

    // get a list of matches for a given user

    @PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
    @GetMapping("/blindDate/{userId}/matches")
    public ResponseEntity getMatches(HttpServletRequest request, @PathVariable String userId) {

        HttpSession session = request.getSession();

//        String sessionId = session.getId();

        // 세션 id 및 토큰을 헤더에서 추출
        String sessionId = request.getHeader("sessionId");
        String token = request.getHeader("Authorization");
        log.info(sessionId);

//        if(isValidToken(token,sessionId)){
        // 인증된 사용자에 대한 로직 수행

        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserId = authentication.getName();


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