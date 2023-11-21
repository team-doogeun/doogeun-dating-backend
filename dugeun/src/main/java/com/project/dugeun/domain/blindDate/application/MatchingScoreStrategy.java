package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.user.domain.User;

public interface MatchingScoreStrategy {

    int calculateScore(User user1, User user2);

}
