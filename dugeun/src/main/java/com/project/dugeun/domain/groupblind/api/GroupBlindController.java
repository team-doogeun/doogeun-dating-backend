package com.project.dugeun.domain.groupblind.api;


import com.project.dugeun.domain.base.rq.RequestUser;
import com.project.dugeun.domain.groupblind.application.GroupBlindService;
import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.dto.ExitRoomResponseDto;
import com.project.dugeun.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
@Setter
public class GroupBlindController {


    private final RequestUser requestUser;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupBlindRepository groupBlindRepository;

    @Autowired
    private GroupBlindService groupBlindService;


    // 다른 사용자가 URL을 통해 미팅방에 입장하는 것을 막기 위해 본인인지를 검증하는 부분을 추가
    // Spring Security의 Principal 객체를 사용하여 현재 인증된 사용자의 정보 가져오기
    @PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
    @GetMapping("/{title}")
    public ResponseEntity enterRoom(@PathVariable String title,Principal principal){


        // userId가 본인일 경우에만 해당 방에 들어갈 수 있도록 검증
        // 해당 미팅방에는 본인만이 접근할 수 있도록 보장
        if(!requestUser.getMember().getUserId().equals(principal.getName()))
        {
            String responseMessage = "해당 미팅방에는 접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

        groupBlindService.enter(groupBlindRepository.findByTitle(title),requestUser.getMember());


        String responseMessage = "미팅방에 입장하였습니다";
        return ResponseEntity.ok(responseMessage);

                 }



   @PreAuthorize("isAuthenticated()")
   @PostMapping("/{title}/exit")
    public ResponseEntity exit(@PathVariable String title){

        groupBlindService.exit(groupBlindRepository.findByTitle(title), requestUser.getMember());

        // 응답처리
       EntityModel<ExitRoomResponseDto> entityModel = EntityModel.of(new ExitRoomResponseDto((requestUser.getMember())));
       return ResponseEntity.ok(entityModel);

   }


    }

