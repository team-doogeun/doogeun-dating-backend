package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.domain.Filter;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.user.domain.User;

import java.util.List;

public interface MatchService {

    public List<Match> getAllMatches(String userId);

    public void saveMatch(Match match);

    public List<User> getPotentialMatches(String userId, List<Filter> filters);
}
