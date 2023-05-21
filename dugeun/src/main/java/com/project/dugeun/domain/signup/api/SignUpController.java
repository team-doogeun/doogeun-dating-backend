package com.project.dugeun.domain.signup.api;


import com.project.dugeun.domain.signup.application.SignupService;
import com.project.dugeun.domain.signup.dto.UserSaveRequestDto;
import com.project.dugeun.domain.signup.dto.UserSaveResponseDto;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.signup.application.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@RequestMapping("/users")
@Controller
@RequiredArgsConstructor
@Slf4j
@ResponseBody
public class SignUpController {
    private final S3Service s3Service;
    private final SignupService signupService;


    @GetMapping(value="/signup")
    public int signup(){
        return 0;
    }


//    @PostMapping(value =  "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins =  "http://www.localhost:3000")
    @PostMapping(value =  "/signup", consumes = {"multipart/form-data"})
    public ResponseEntity signup(
            @Valid @RequestPart(value="user", required=true) UserSaveRequestDto user,
            Errors errors,
            @RequestPart(value="basicFilePath",required = true) MultipartFile basicFilePath,
            @RequestPart(value="secondFilePath",required = true) MultipartFile secondFilePath,
            @RequestPart(value="thirdFilePath",required = true) MultipartFile thirdFilePath
    ) throws IOException {
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        String imgPath1 = s3Service.upload(basicFilePath);
        user.setBasicFilePath(imgPath1);
        String imgPath2 = s3Service.upload(secondFilePath);
        user.setSecondFilePath(imgPath2);
        String imgPath3 = s3Service.upload(thirdFilePath);
        user.setThirdFilePath(imgPath3);


        User savedUser = signupService.saveUser(user);
        EntityModel<UserSaveResponseDto> entityModel = EntityModel.of(new UserSaveResponseDto(savedUser));
        entityModel.add(linkTo(SignUpController.class).slash("signup").withRel("signup"));
//        return ResponseEntity.badRequest().body(errors.getAllErrors());
        return ResponseEntity.ok(entityModel);
    }



}