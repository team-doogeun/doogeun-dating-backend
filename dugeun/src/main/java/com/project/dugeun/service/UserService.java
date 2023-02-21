package com.project.dugeun.service;

import com.project.dugeun.dto.UserSaveRequestDto;
import com.project.dugeun.entity.user.User;
import com.project.dugeun.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService  {

private final UserRepository userRepository;
private  final PasswordEncoder passwordEncoder;

public User saveUser(UserSaveRequestDto user){
    User byUserId = userRepository.findByUserId(user.getUserId());
    if(byUserId != null){
        throw new IllegalStateException("이미 가입된 회원입니다.. ");
        // 에러 메시지
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





//public User saveUser(User user){
//validateDuplicateUser(user);
//return userRepository.save(user);
//}
//
//private void validateDuplicateUser(User user){
//    User findUser = userRepository.findByExternalId(user.getExternalId());
//    if(findUser != null){
//        throw new IllegalStateException("이미 가입된 회원입니다 . ");
//    }
//}
}


