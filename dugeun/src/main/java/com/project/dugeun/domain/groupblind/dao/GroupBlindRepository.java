package com.project.dugeun.domain.groupblind.dao;

import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.GroupBlindStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupBlindRepository extends JpaRepository<GroupBlindRoom, Long> {


    GroupBlindRoom findByTitle(String title);

    GroupBlindRoom findByRoomId(Integer roomId);

    List<GroupBlindRoom> findByHostId(String hostId);

    List<GroupBlindRoom> findByHostIdAndGroupBlindStatus(String userId, GroupBlindStatus status);
}
