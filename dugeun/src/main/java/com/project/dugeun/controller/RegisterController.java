package com.project.dugeun.controller;


import com.project.dugeun.model.dto.UserSaveRequestDto;
import com.project.dugeun.model.dto.UserSaveResponseDto;
import com.project.dugeun.model.domain.user.User;
import com.project.dugeun.service.S3Service;
import com.project.dugeun.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/users")
@Controller
@RequiredArgsConstructor
@Slf4j
public class RegisterController {
    private final S3Service s3Service;
    private final UserService userService;

    @PostMapping(value =  "/signup")
    public ResponseEntity signup(
            @Valid @RequestPart UserSaveRequestDto user,
            @RequestPart(required = false) MultipartFile basicFilePath,
            @RequestPart(required = false) MultipartFile secondFilePath,
            @RequestPart(required = false) MultipartFile thirdFilePath,

            Errors errors
    ) throws IOException {
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        String imgPath1 = s3Service.upload(basicFilePath);
        user.setBasicFilePath(imgPath1);
        String imgPath2 = s3Service.upload(secondFilePath);
        user.setSecondFilePath(imgPath2);
        String imgPath3 = s3Service.upload(thirdFilePath);
        user.setThirdFilePath(imgPath3);


        User savedUser = userService.saveUser(user);
        EntityModel<UserSaveResponseDto> entityModel = EntityModel.of(new UserSaveResponseDto(savedUser));
        entityModel.add(linkTo(RegisterController.class).slash("signup").withRel("signup"));
        return ResponseEntity.ok(entityModel);
    }



}
