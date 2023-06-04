package com.project.dugeun.domain.signup.application;

import com.project.dugeun.domain.signup.dto.UserSaveRequestDto;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignupService {

private final UserRepository userRepository;
private  final PasswordEncoder passwordEncoder;


// 프론트 단에서  konkuk.ac.kr을 박아둬서 이 코드는 필요 x
// public boolean isValidEmail(String email){
//}

@Transactional
public User saveUser(UserSaveRequestDto user){
    User byUserId = userRepository.findByUserId(user.getUserId());
    if(byUserId != null){
        throw new IllegalStateException("이미 가입된 유저 아이디 입니다.. ");
        // 에러 메시지
    }
    if(userRepository.findByStudentId(user.getStudentId())!=null){
        throw new IllegalStateException("이미 가입된 학번의 회원 입니다.. ");
    }
   // 이메일 , 학번 예외처리 ( 같은 이메일 있으면 안되게 하기, 학번 가트면 처리 안되게 하기 )
    if(userRepository.findByEmail(user.getEmail())!=null){
        throw new IllegalStateException("이미 가입된 이메일 입니다.. ");

    }


    return userRepository.save(User.builder()
            .userId(user.getUserId())
            .name(user.getName())
            .externalId(user.getExternalId())
                    .basicFilePath(user.getBasicFilePath())
                    .secondFilePath(user.getSecondFilePath())
                    .thirdFilePath(user.getThirdFilePath())
            .studentId(user.getStudentId())
                    .email(user.getEmail())
                    .age(user.getAge())
                    .gender(user.getGender())
                    .detailProfile(user.getDetailProfile())
                    .idealTypeProfile(user.getIdealTypeProfile())
            .password(passwordEncoder.encode(user.getPassword()))
            .build());
       }
}


