package com.project.dugeun.domain.groupblind.dao;

import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupBlindRepository extends JpaRepository<GroupBlindRoom, Long> {


    GroupBlindRoom findByTitle(String title);

    GroupBlindRoom findByRoomId(Integer roomId);
}
