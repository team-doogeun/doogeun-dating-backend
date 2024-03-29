package com.project.dugeun.domain.blindDate.dao;

import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {
    List<Match> findByUser1(User user1);
    Match findByUser1AndUser2(User user1, User user2);
    boolean existsByUser1AndUser2(User user1, User user2);
 }


