package com.project.dugeun.domain.signup.dao;

import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByExternalId(String externalId);

    User findByUserId(String userId);

}
