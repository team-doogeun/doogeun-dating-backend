package com.project.dugeun.domain.signin.application;

import com.project.dugeun.domain.signin.dto.UserSignInRequestDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public Boolean login(UserSignInRequestDto loginRequest){
        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();

        // userId를 이용해 사용자 정보를 조회합니다.
        User user = userRepository.findByUserId(userId);
        return validatePassword(user, password);
        }


    private boolean validatePassword(User user, String password){

        return user.getPassword().equals(password);
    }
}
