package com.project.dugeun.domain.signup.application;

import com.project.dugeun.domain.signup.dto.EmailVerificationRequestDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.UserStatus;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CertService {

    private  final UserRepository userRepository;

    @Value("${api.key}")
    private String apiKey;


    @Async
    public CompletableFuture<Boolean> sendVerificationEmailAsync(String email, String uniName) {

        boolean isSend = false;
        try {
             isSend = startEmailVerification(email, uniName);
            return CompletableFuture.completedFuture(isSend);
        } catch (IOException e) {
            log.error("CertService error");
            return CompletableFuture.completedFuture(false);
        }
    }

    public boolean startEmailVerification(String email, String uniName) throws IOException {
        Boolean isSend = false;
        Map<String, Object> validation = UnivCert.certify(apiKey, email, uniName, false);
        if(validation.get("success").equals(true))
        {
            isSend = true;
        }

        return isSend;
    }

    public boolean checkCode(int code,String email,String univName) throws IOException {

        boolean isCorrect = false;
        Map<String, Object> validation =UnivCert.certifyCode(apiKey,email,univName,code);
        if(validation.get("success").equals(true))
        {
            isCorrect = true;
        }
        return isCorrect;
    }

    public boolean clearUsers() throws IOException {

        Map<String, Object> validation = UnivCert.clear(apiKey);
        return validation.get("success").equals(true);
    }

    @Transactional
    public void validateAndChangeUserStatus(boolean isCorrect, EmailVerificationRequestDto emailVerificationRequestDto){
        if(isCorrect){
            User user = userRepository.findByEmail(emailVerificationRequestDto.getEmail());
            user.setUserStatus(UserStatus.COMPLETED);
        }
    }



}
