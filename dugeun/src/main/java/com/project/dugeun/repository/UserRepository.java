package com.project.dugeun.repository;

import com.project.dugeun.model.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByExternalId(String externalId);

    User findByUserId(String userId);

}
