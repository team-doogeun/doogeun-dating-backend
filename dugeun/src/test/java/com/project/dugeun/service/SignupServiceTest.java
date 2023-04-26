package com.project.dugeun.service;


import com.project.dugeun.domain.signup.application.SignupService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
class SignupServiceTest {

    @Autowired
    SignupService signupService;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Test
    @DisplayName("회원가입 테스트트")
    public void saveUserTest(){

    }
}
