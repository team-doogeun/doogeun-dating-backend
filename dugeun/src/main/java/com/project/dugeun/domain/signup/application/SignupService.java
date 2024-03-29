package com.project.dugeun.domain.signup.application;

import com.project.dugeun.domain.signup.dto.UserSaveRequestDto;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SignupService {
private final UserRepository userRepository;
private final CertService certService;


@Transactional
public User saveUser(UserSaveRequestDto user){

    log.info("메일 보내기 Thread 호출 전");
    certService.sendVerificationEmailAsync(user.getEmail(), user.getUniName())
            .exceptionally(ex -> {
                log.error("Email verification failed to send for: {}", user.getEmail(), ex);
                // 이메일 전송 실패 시 처리할 로직 추가
                return false;
            })
            .thenAccept(isSend -> {
                if (isSend) {
                    log.info("Email verification started for: {}", user.getEmail());
                    // 이메일 전송 성공 시 처리할 로직 추가
                }
            });

    log.info("해당 아이디 관련 유저 조회 호출 전 ");
    User byUserId = userRepository.findByUserId(user.getUserId());
    if(byUserId != null){
        throw new IllegalStateException("이미 가입된 유저 아이디 입니다..");
    }
    log.info("해당 아이디 관련 유저 조회 호출 후 ");


    log.info("해당 이름 관련 유저 조회 호출 전 ");
    if(userRepository.findByName(user.getName())!=null){
        throw new IllegalStateException("이미 가입된 유저의 이름 입니다..");
    }
    log.info("해당 이름 관련 유저 조회 호출 후 ");

    log.info("해당 이메일 관련 유저 조회 호출 전 ");
    // 이메일 , 학번 예외처리 ( 같은 이메일 있으면 안되게 하기, 학번 가트면 처리 안되게 하기 )
    if(userRepository.findByEmail(user.getEmail())!=null){
        throw new IllegalStateException("이미 가입된 이메일 입니다..");

    }
    log.info("해당 이메일 관련 유저 조회 호출 후 ");

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


