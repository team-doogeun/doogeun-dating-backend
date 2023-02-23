package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.domain.Match;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {
    
    @Override
    public void match() {

    }

    @Override
    public Optional<Match> findMatch(String userId) {
        return Optional.empty();
    }
}
