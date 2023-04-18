package com.project.dugeun.domain.blindDate.api;

import com.project.dugeun.domain.blindDate.application.MatchMaker;
import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.blindDate.domain.dto.MatchResponseDto;
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
        List<Match> matches = matchRepository.findByUser1(user);



//        User matchedUser = targetUsers.get(0);
//        List<Match> matchesPerUser1 = matchedUser.getMatchings();
//
//
//        User matchedUser2 = targetUsers.get(1);
//        List<Match> matchesPerUser2 = matchedUser2.getMatchings();

        User matchedUser = matches.get(0).getUser2();
        User matchedUser2 = matches.get(1).getUser2();

        EntityModel<MatchResponseDto> entityModel = EntityModel.of(new MatchResponseDto(matchedUser,matchedUser2));
        return ResponseEntity.ok(entityModel);


//        return matches;

    }


}