package com.project.dugeun.domain.groupblind.api;


import com.project.dugeun.domain.groupblind.dao.GroupBlindRepository;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group_blind/rooms")
public class GroupBlindController {

    @Autowired
    private GroupBlindRepository groupBlindRepository;

    @PostMapping
    public GroupBlindRoom createRoom(@RequestBody GroupBlindRoom groupBlindRoom) {

        return groupBlindRepository.save(groupBlindRoom);
    }

    @GetMapping
    public List<GroupBlindRoom> getRooms() {

        return groupBlindRepository.findAll();
    }
}
