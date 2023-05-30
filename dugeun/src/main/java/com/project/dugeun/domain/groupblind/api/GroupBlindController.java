package com.project.dugeun.domain.groupblind.api;


import com.project.dugeun.domain.groupblind.application.GroupBlindService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
@Setter
public class GroupBlindController {


    private final Rq rq;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupBlindRepository groupBlindRepository;

    @Autowired
    private GroupBlindService groupBlindService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{userId}/new")
    public ResponseEntity create(@PathVariable String userId, Principal principal,@Valid @RequestBody RoomSaveRequestDto room){

        System.out.println("rq.getMember() ===> ");
        System.out.println(rq.getMember());
        System.out.println("principal.getName() ===> ");
        System.out.println(principal.getName());
        System.out.println("RoomSaveRequestDto room ===> ");
        System.out.println(room.getTitle());
        System.out.println(room.getCapacityMale());
        System.out.println(room.getCapacityFemale());

        // userId가 본인일 겨우에만 해당 방 만들 수 있도록 검증
        if(!userId.equals(principal.getName()))
        {
            String responseMessage = "미팅방을 만들 수 없습니다";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }


        GroupBlindRoom savedRoom =  groupBlindService.createMeetingRoom(room);


        EntityModel<RoomSaveResponseDto> entityModel = EntityModel.of(new RoomSaveResponseDto(savedRoom));

        return ResponseEntity.ok(entityModel);
    }




    // 다른 사용자가 URL을 통해 미팅방에 입장하는 것을 막기 위해 본인인지를 검증하는 부분을 추가
    // Spring Security의 Principal 객체를 사용하여 현재 인증된 사용자의 정보 가져오기
    @PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
    @GetMapping("/{title}")
    public ResponseEntity enterRoom(@PathVariable String title,Principal principal){


        // userId가 본인일 경우에만 해당 방에 들어갈 수 있도록 검증
        // 해당 미팅방에는 본인만이 접근할 수 있도록 보장
        if(!rq.getMember().getUserId().equals(principal.getName()))
        {
            String responseMessage = "해당 미팅방에는 접근할 수 없습니다.";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }

        groupBlindService.enter(groupBlindRepository.findByTitle(title),rq.getMember());


        String responseMessage = "미팅방에 입장하였습니다";
        return ResponseEntity.ok(responseMessage);

    }



    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{title}/exit")
    public ResponseEntity exit(@PathVariable String title){

        groupBlindService.exit(groupBlindRepository.findByTitle(title), rq.getMember());

        // 응답처리
        EntityModel<ExitRoomResponseDto> entityModel = EntityModel.of(new ExitRoomResponseDto((rq.getMember())));
        return ResponseEntity.ok(entityModel);

    }
}


//    @DeleteMapping("/delete/{roomId}")
//    public ResponseEntity<String> deleteMeetingRoom(@PathVariable Long roomId) {
//        // Check if the user is authenticated
//        if (requestUser == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
//        }
//
//        // Delete the meeting room
//        boolean deleted = groupBlindService.deleteMeetingRoom(roomId, requestUser.getMember());
//
//        if (deleted) {
//            return ResponseEntity.ok("Meeting room deleted successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }