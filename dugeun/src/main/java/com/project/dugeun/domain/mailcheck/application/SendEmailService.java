package com.project.dugeun.domain.mailcheck.application;

import com.project.dugeun.domain.mailcheck.dto.MailDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendEmailService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    private static final String FROM_ADDRESS = "doogeunS2@gmail.com";

    @Transactional
    public MailDto createMailAndChangePassword(String userEmail, String userName) {

        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle(userName+"님의 임시비밀번호 안내 이메일입니다.");
        dto.setMessage("안녕하세요. DOOGEUN 임시비밀번호 안내 관련 메일입니다." + "[" + userName + "]" + "님의 임시 비밀번호는 "
        + str + " 입니다.");
        updatePassword(str, userEmail);
        return dto;
    }

    @Transactional
    public void updatePassword(String str,String userEmail){
        String pw = passwordEncoder.encode(str);
        User user = userRepository.findByUserId(userEmail);
        user.setPassword(pw);
    }

    public String getTempPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(MailDto mailDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}