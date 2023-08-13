package com.project.dugeun.domain.signup.api;


import com.project.dugeun.domain.signup.application.SignupService;
import com.project.dugeun.domain.signup.dto.*;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.signup.application.S3Service;

import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import javax.validation.Valid;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@RequestMapping("/users")
@Controller
@RequiredArgsConstructor
@Slf4j
@ResponseBody
public class SignUpController {
    private final S3Service s3Service;
    private final SignupService signupService;

    @CrossOrigin(origins =  "http://www.localhost:3000")
    @PostMapping(value =  "/signup", consumes = {"multipart/form-data"})
    public ResponseEntity<UserSaveResponseDto> signup(
            @Valid @RequestPart(value="user", required=true) UserSaveRequestDto user,
            @RequestPart(value="basicFilePath",required = true) MultipartFile basicFilePath,
            @RequestPart(value="secondFilePath",required = true) MultipartFile secondFilePath,
            @RequestPart(value="thirdFilePath",required = true) MultipartFile thirdFilePath
                                ) throws IOException {
        String imgPath1 = s3Service.upload(basicFilePath);
        user.setBasicFilePath(imgPath1);
        String imgPath2 = s3Service.upload(secondFilePath);
        user.setSecondFilePath(imgPath2);
        String imgPath3 = s3Service.upload(thirdFilePath);
        user.setThirdFilePath(imgPath3);

        User savedUser = signupService.saveUser(user);
        EntityModel<UserSaveResponseDto> entityModel = EntityModel.of(new UserSaveResponseDto(savedUser));
        entityModel.add(linkTo(SignUpController.class).slash("signup").withRel("signup"));

        UserSaveResponseDto userSaveResponseDto = UserSaveResponseDto.builder().userId(user.getUserId()).name(user.getName()).build();

        return ResponseEntity.ok()
                .body(userSaveResponseDto);
    }

    @PostMapping(value = "/api/sendEmail")
    public ResponseEntity<EmailSendResponseDto> receiveEmail(@RequestBody EmailSendRequestDto emailSendRequestDto) throws IOException {
        // 여기서 받은 email을 활용하여 필요한 처리 수행
        boolean isSend = signupService.startEmailVerification(emailSendRequestDto.getEmail(), emailSendRequestDto.getUniName());
        EmailSendResponseDto emailSendResponseDto = new EmailSendResponseDto();
        emailSendResponseDto.setSuccess(isSend);
        return ResponseEntity.ok()
                .body(emailSendResponseDto);
    }


    @PostMapping(value = "/api/code")
    public ResponseEntity<EmailVerificationResponseDto> receiveCode(@RequestBody EmailVerificationRequestDto emailVerificationRequestDto) throws IOException {
        // 여기서 받은 code를 활용하여 필요한 처리 수행
        boolean isCorrect = signupService.checkCode(emailVerificationRequestDto.getCode());
        EmailVerificationResponseDto emailVerificationResponseDto = new EmailVerificationResponseDto();
        emailVerificationResponseDto.setSuccess(isCorrect);
        return ResponseEntity.ok()
                .body(emailVerificationResponseDto);

    }

}