package com.project.dugeun.domain.blindDate.api;

import com.project.dugeun.domain.blindDate.application.MatchMaker;
import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.blindDate.domain.dto.MatchResponseDto;
import com.project.dugeun.domain.blindDate.domain.dto.OneMatchResponseDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchMaker matchMaker;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchRepository matchRepository;

    // get a list of matches for a given user
    @GetMapping("/blindDate/{userId}/matches")
    public ResponseEntity getMatches(@PathVariable String userId) {



        // TODO - 예외처리

        User user = userRepository.findByUserId(userId);

        matchMaker.manageMatches(user);
        // 일단 가장 점수가 높은 순으로 소개해주기 (변경 가능)
        List<Match> matches = matchRepository.findByUser1(user);

        EntityModel<MatchResponseDto> twoPersonEntityModel = null;
        EntityModel<OneMatchResponseDto> onePersonEntityModel = null;


        // TODO - 소개될 최소 2명의 유저가 없을 때 예외 처리
        if (matches.size()>=2) {
            User matchedUser = matches.get(0).getUser2();
            User matchedUser2 = matches.get(1).getUser2();
            twoPersonEntityModel = twoPersonEntityModel.of(new MatchResponseDto(matchedUser,matchedUser2));

            return ResponseEntity.ok(twoPersonEntityModel);
        }
        else if (matches.size()==1) {
            User matchedUser = matches.get(0).getUser2();
            onePersonEntityModel = onePersonEntityModel.of(new OneMatchResponseDto(matchedUser));

            return ResponseEntity.ok(onePersonEntityModel);
        }


        return null;
    }
}