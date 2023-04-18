package com.project.dugeun.domain.finalMatch.dao;

import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinalMatchRepository extends JpaRepository<FinalMatch, Long> {

    List<FinalMatch> findByFirstUser(User user1);

    List<FinalMatch> findBySecondUser(User user2);


}
