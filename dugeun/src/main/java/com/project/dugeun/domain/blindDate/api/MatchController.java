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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
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

        User user = userRepository.findByUserId(userId);

        List<Match> pair = new ArrayList<>();
        pair = matchRepository.findByUser1(user);


        EntityModel<MatchResponseDto> twoPersonEntityModel = null;
        EntityModel<OneMatchResponseDto> onePersonEntityModel = null;


        if(pair.size() == 2){
            User matchedUser = pair.get(0).getUser2();
            User matchedUser2 = pair.get(1).getUser2();
//            User matchedUser = pair.get(0);
//            User matchedUser2 = pair.get(1);
            twoPersonEntityModel = twoPersonEntityModel.of(new MatchResponseDto(matchedUser,matchedUser2));

            return ResponseEntity.ok(twoPersonEntityModel);
        }
        else if (pair.size() ==1)
        {
            User matchedUser = pair.get(0).getUser2();
            onePersonEntityModel = onePersonEntityModel.of(new OneMatchResponseDto(matchedUser));

            return ResponseEntity.ok(onePersonEntityModel);
        }
        return null;
    }
}