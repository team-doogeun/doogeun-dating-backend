package com.project.dugeun.domain.blindDate.api;

import com.project.dugeun.domain.blindDate.application.MatchMaker;
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

    // get a list of matches for a given user
    @GetMapping("/users/{userId}/matches")
    public ResponseEntity getMatches(@PathVariable String userId) {

        // TODO - 예외처리
        User user = userRepository.findByUserId(userId);
        List<User> matches = matchMaker.findMatches(user);
        User matchedUser = matches.get(0);
        EntityModel<MatchResponseDto> entityModel = EntityModel.of(new MatchResponseDto(matchedUser));
        return ResponseEntity.ok(entityModel);


//        return matches;

    }


}