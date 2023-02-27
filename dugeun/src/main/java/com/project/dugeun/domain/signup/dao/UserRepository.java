package com.project.dugeun.domain.signup.dao;

import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByExternalId(String externalId);

    User findByUserId(String userId);



}
