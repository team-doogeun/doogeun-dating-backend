package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.domain.Match;

import java.util.Optional;

public interface MatchService {

public void match();
public Optional<Match> findMatch(String userId);


}
