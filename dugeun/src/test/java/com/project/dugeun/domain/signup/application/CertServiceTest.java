package com.project.dugeun.domain.signup.application;

import com.project.dugeun.domain.user.dao.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CertServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UnivCertService univCertService;
    @InjectMocks
    private CertService certService;



    @Test
    @DisplayName("이메일 보내기 성공")
    void testSendVerificationEmail() throws Exception {
        // UnivCert.certify가 true를 반환하도록 설정
        when(univCertService.certify(anyString(), anyString()))
                .thenReturn(Collections.singletonMap("success", true));

        // CompletableFuture 객체가 완료될 때까지 기다리기 위해 get() 메서드를 사용
        boolean result = certService.sendVerificationEmailAsync("test@example.com", "university").get();

        // 결과 검증: 이메일 전송 성공
        assertTrue(result);
    }

    @Test
    @DisplayName("이메일 보내기 실패")
    void testSendVerificationEmailFailure() throws Exception {
        // UnivCert.certify가 true를 반환하도록 설정
        when(univCertService.certify(anyString(), anyString()))
                .thenReturn(Collections.singletonMap("success", false));

        // CompletableFuture 객체가 완료될 때까지 기다리기 위해 get() 메서드를 사용
        boolean result = certService.sendVerificationEmailAsync("test@example.com", "university").get();

        // 결과 검증: 이메일 전송 성공
        assertFalse(result);
    }

    @Test
    @DisplayName("코드 검증 성공")
    void testCheckCode() throws Exception {
        // UnivCertService.certifyCode가 true를 반환하도록 설정
        when(univCertService.certifyCode(anyString(), anyString(), anyInt()))
                .thenReturn(Collections.singletonMap("success", true));

        // 결과 검증: 코드 검증 성공
        assertTrue(certService.checkCode(12345, "test@example.com", "university"));
    }

    @Test
    @DisplayName("코드 검증 실패")
    void testCheckCodeFailure() throws Exception {
        // UnivCertService.certifyCode가 false를 반환하도록 설정
        when(univCertService.certifyCode(anyString(), anyString(), anyInt()))
                .thenReturn(Collections.singletonMap("success", false));

        // 결과 검증: 코드 검증 실패
        assertFalse(certService.checkCode(12345, "fail@example.com", "university"));
    }
}