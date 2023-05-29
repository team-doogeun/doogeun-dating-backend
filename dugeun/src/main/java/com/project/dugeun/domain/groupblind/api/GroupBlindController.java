package com.project.dugeun.domain.groupblind.api;


import com.project.dugeun.domain.base.rq.RequestUser;
import com.project.dugeun.domain.groupblind.application.GroupBlindService;
import com.project.dugeun.domain.groupblind.dto.CreateGroupBlindRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groupBlind")
@RequiredArgsConstructor
public class GroupBlindController {

    private final RequestUser requestUser;
    private final GroupBlindService groupBlindService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{userId}/new")
    public ResponseEntity<Long> create(@RequestBody CreateGroupBlindRequest request) {
        // Check if the user is authenticated
//        if (!requestUser.isLogin()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
//        }

        // Create the meeting room
        Long roomId = groupBlindService.createMeetingRoom(request);

        return ResponseEntity.ok().body(roomId);
    }

        @DeleteMapping("/delete/{roomId}")
        public ResponseEntity<String> deleteMeetingRoom(@PathVariable Long roomId) {
            // Check if the user is authenticated
            if (requestUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
            }

            // Delete the meeting room
            boolean deleted = groupBlindService.deleteMeetingRoom(roomId, requestUser.getMember());

            if (deleted) {
                return ResponseEntity.ok("Meeting room deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        }

//        @PostMapping("/{roomId}/start")
//        public ResponseEntity<String> startMeetingRoom(@PathVariable Long roomId) {
//            groupBlindService.startMeetingRoom(roomId);
//            return ResponseEntity.ok("Meeting room started successfully");
//        }

}