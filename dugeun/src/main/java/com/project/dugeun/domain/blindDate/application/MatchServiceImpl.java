package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.domain.Filter;
import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService{
    @Override
    public List<Match> getAllMatches(String userId) {
        return null;
    }

    @Override
    public void saveMatch(Match match) {

    }

    @Override
    public List<User> getPotentialMatches(String userId, List<Filter> filters) {
        return null;
    }
}
