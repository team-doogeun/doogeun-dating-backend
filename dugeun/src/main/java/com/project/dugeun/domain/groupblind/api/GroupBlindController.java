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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


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


    @PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
    @GetMapping("/{title}")
    public ResponseEntity enterRoom(@PathVariable String title){

        groupBlindService.enter(groupBlindRepository.findByTitle(title),requestUser.getMember());


        // 응답처리
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

