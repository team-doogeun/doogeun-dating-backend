package com.project.dugeun.domain.blindDate.dao;

import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {



}
