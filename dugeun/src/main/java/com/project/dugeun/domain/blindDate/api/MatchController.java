package com.project.dugeun.domain.blindDate.api;

import com.project.dugeun.domain.blindDate.application.MatchMaker;
import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.blindDate.dao.dto.MatchResponseDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@RestController
@Slf4j
@RequiredArgsConstructor
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
    public ResponseEntity getMatches(Principal principal, @PathVariable String userId) {


    if(!userId.equals(principal.getName())){
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