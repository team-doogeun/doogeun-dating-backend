package com.project.dugeun.domain.user.dao;

import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByExternalId(String externalId);

    User findByUserId(String userId);

    User findByEmail(String email);

    User findByStudentId(String studentId);

    Optional<User> findUserByUserId(String userId);


    List<User> findAllByUserIdNot(String userId);
}
