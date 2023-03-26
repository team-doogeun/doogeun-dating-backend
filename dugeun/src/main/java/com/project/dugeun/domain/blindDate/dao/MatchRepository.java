package com.project.dugeun.domain.blindDate.dao;

import com.project.dugeun.domain.blindDate.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match,Long> {


}
