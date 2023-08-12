package com.project.dugeun.domain.signup.application;

import com.project.dugeun.domain.signup.dto.UserSaveRequestDto;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignupService {
private final UserRepository userRepository;
String userEmail;
String userUniName;

@Value("${api.key}")
private String apiKey;

@Transactional
public User saveUser(UserSaveRequestDto user){
    User byUserId = userRepository.findByUserId(user.getUserId());
    if(byUserId != null){
        throw new IllegalStateException("이미 가입된 유저 아이디 입니다.. ");
    }
    if(userRepository.findByStudentId(user.getStudentId())!=null){
        throw new IllegalStateException("이미 가입된 학번의 회원 입니다.. ");
    }
   // 이메일 , 학번 예외처리 ( 같은 이메일 있으면 안되게 하기, 학번 가트면 처리 안되게 하기 )
    if(userRepository.findByEmail(user.getEmail())!=null){
        throw new IllegalStateException("이미 가입된 이메일 입니다.. ");
    }

    if (user.getEmail().equals("zox004@konkuk.ac.kr")) {
        throw new IllegalStateException("불량 회원 입니다.");
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
                    .password(user.getPassword())
            .build());
       }


    public boolean startEmailVerification(String email, String uniName) throws IOException {
        userEmail = email;
        userUniName =uniName;
        boolean isSend = false;
        Map<String, Object> validation = UnivCert.certify(apiKey, email, uniName, true);
        if(validation.get("success").equals("true"))
        {
            isSend = true;
        }

        return isSend;
    }

    public boolean checkCode(int code) throws IOException {

    boolean isCorrect = false;
    Map<String, Object> validation =UnivCert.certifyCode(apiKey,userEmail,userUniName,code);
    if(validation.get("success").equals("true"))
    {
        isCorrect = true;
    }
    return isCorrect;
    }



}


