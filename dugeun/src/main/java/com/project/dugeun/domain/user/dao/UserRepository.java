package com.project.dugeun.domain.user.dao;

import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByExternalId(String externalId);

    User findByUserId(String userId);

    User findByEmail(String email);

    User findByStudentId(String studentId);

    List<User> findAllByUserIdNot(String userId);



    @Query(value = "SELECT * FROM user ORDER BY RAND() LIMIT 1", nativeQuery = true)
    User findRandomUser();


    @Query(value = "SELECT * FROM user WHERE gender != :gender ORDER BY RAND() LIMIT 1", nativeQuery = true)
    User findRandomUserByGenderNot(@Param("gender") GenderType gender);

    List<User> findByGenderNotAndUserIdNot(String gender, String userId);

    List<User> findByGenderNotAndUserIdNotIn(GenderType gender, List<String> collect);

    void deleteByUserId(String userId);
}
