//package com.project.dugeun.domain.groupblind.api;
//
//
//import com.project.dugeun.domain.groupblind.application.GroupBlindService;
//import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
//import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
//import com.project.dugeun.domain.user.domain.profile.category.GenderType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/group_blind")
//public class GroupBlindController {
//
//    //    private final GroupBlindRepository groupBlindRepository;
////    @Autowired
////    private GroupndService groupBliBlindService;
//    @Autowired
//    private GroupBlindRepository groupBlindRepository;
//
////    @Autowired
////    public GroupBlindController(GroupBlindRepository groupBlindRepository) {
////        this.groupBlindRepository = groupBlindRepository;
////    }
//
//    @PostMapping("/create")
//    public ResponseEntity<GroupBlindRoom> createRoom(
//            @RequestParam(value = "capacity") int capacity,
//            @RequestParam(value = "gendertype") GenderType genderType) {
//
////        GroupBlindRoom groupBlindRoom = groupBlindService.createGroupBlind(capacity, genderType);
////        return ResponseEntity.ok(groupBlindRoom);
//    }
//
////    @GetMapping("/list")
////    public List<GroupBlindRoom> listGroupBlindRooms(@RequestParam int capacity, @RequestParam GenderType genderType) {
////
////
//////        return groupBlindRepository.findByCapacityAndGenderType(capacity, genderType);
////    }
//
//    @GetMapping("/{id}")
//    public GroupBlindRoom getGroupBlindRoom(@PathVariable Long id) {
//
////        return groupBlindService.getGroupBlind();
//    }
//}
