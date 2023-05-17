package com.project.dugeun.domain.mailcheck.application;

import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private UserRepository userRepository;

    public boolean userEmailCheck(String userEmail, String userName) {

        User user = userRepository.findByUserId(userEmail);
        if(user!=null && user.getName().equals(userName)) {
            return true;
        }
        else {
            return false;
        }
    }
}
