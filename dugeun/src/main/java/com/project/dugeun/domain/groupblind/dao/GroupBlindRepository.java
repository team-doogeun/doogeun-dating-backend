package com.project.dugeun.domain.groupblind.dao;

import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupBlindRepository extends JpaRepository<GroupBlindRoom, Long> {


    GroupBlindRoom findByTitle(String title);

    GroupBlindRoom findByRoomId(Integer roomId);
}
