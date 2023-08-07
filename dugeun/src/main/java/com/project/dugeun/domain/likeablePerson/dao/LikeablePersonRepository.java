package com.project.dugeun.domain.likeablePerson.dao;

import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeablePersonRepository extends JpaRepository<LikeablePerson,Long> {

    List<LikeablePerson> findByFromUser(User user1);

    List<LikeablePerson> findByToUser(User user2);

    LikeablePerson findByFromUserAndToUser(User fromUser, User toUser);

}
