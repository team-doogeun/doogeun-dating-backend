package com.project.dugeun.domain.email.api;

import com.project.dugeun.domain.email.application.MailService;
import com.project.dugeun.domain.email.application.SendEmailService;
import com.project.dugeun.domain.email.dto.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class MailController {


    private final MailService mailService;
    private final SendEmailService sendEmailService;

    @GetMapping("check/findPw")
    public @ResponseBody Map<String, Boolean> pw_find(String userEmail, String userName) {
        Map<String, Boolean> json = new HashMap<>();

        boolean pwFindCheck = mailService.userEmailCheck(userEmail, userName);
        json.put("check", pwFindCheck);
        return json;
    }

    @PostMapping("check/findPw/sendEmail")
    public @ResponseBody void sendMail(String userEmail, String userName) {

        MailDto mailDto = sendEmailService.createMailAndChangePassword(userEmail, userName);
        sendEmailService.mailSend(mailDto);
    }
}