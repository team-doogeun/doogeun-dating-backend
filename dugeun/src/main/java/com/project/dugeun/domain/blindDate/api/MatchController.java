package com.project.dugeun.domain.blindDate.api;

import com.project.dugeun.domain.blindDate.application.MatchMaker;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchMaker matchMaker;

    @Autowired
    private UserRepository userRepository;

    // get a list of matches for a given user
    @GetMapping("/users/{userId}/matches")
    public List<User> getMatches(@PathVariable String userId) {

        // TODO - 예외처리
        User user = userRepository.findByUserId(userId);
        List<User> matches = matchMaker.findMatches(user);
        return matches;

    }


}