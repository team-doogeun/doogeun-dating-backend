package com.project.dugeun.domain.signup.application;

import com.project.dugeun.domain.signup.dto.UserSaveRequestDto;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignupService {
private final UserRepository userRepository;
private final CertService certService;


@Transactional
public User saveUser(UserSaveRequestDto user){
    User byUserId = userRepository.findByUserId(user.getUserId());
    if(byUserId != null){
        throw new IllegalStateException("이미 가입된 유저 아이디 입니다..");
    }

    if(userRepository.findByName(user.getName())!=null){
        throw new IllegalStateException("이미 가입된 유저의 이름 입니다..");
    }
   // 이메일 , 학번 예외처리 ( 같은 이메일 있으면 안되게 하기, 학번 가트면 처리 안되게 하기 )
    if(userRepository.findByEmail(user.getEmail())!=null){
        throw new IllegalStateException("이미 가입된 이메일 입니다..");
    }

    return userRepository.save(User.builder()
            .userId(user.getUserId())
            .name(user.getName())
                    .externalId(user.getExternalId())
                    .description(user.getDescription())
                    .uniName(user.getUniName())
                    .basicFilePath(user.getBasicFilePath())
                    .secondFilePath(user.getSecondFilePath())
                    .thirdFilePath(user.getThirdFilePath())
            .studentId(user.getStudentId())
                    .email(user.getEmail())
                    .age(user.getAge())
                    .gender(user.getGender())
                    .detailProfile(user.getDetailProfile())
                    .idealTypeProfile(user.getIdealTypeProfile())
                    .password(user.getPassword())
            .build());
       }




}


