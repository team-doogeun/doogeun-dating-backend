package com.project.dugeun.domain.user.application;

import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService; // UserService에 Mock 객체 주입

    @Test
    @DisplayName("user 상태 확인")
    void testCheckUserState() {
        // 테스트에 필요한 가상의 User 객체 생성
        User user = new User();
        user.setUserId("12345");
        user.setUserStatus(UserStatus.COMPLETED);

        // Mock 객체 설정
        when(userRepository.findByUserId("12345")).thenReturn(user);

        // 테스트 대상 메소드 호출
        String result = userService.checkUserState("12345");

        // 결과 검증
        assertEquals("completed", result);
    }
}