package com.project.dugeun.domain.groupblind.dao;

import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupBlindRepository extends JpaRepository<GroupBlindRoom, Long> {


    GroupBlindRoom findByTitle(String title);

    GroupBlindRoom findByRoomId(Integer roomId);

    List<GroupBlindRoom> findByHostId(String hostId);
}
