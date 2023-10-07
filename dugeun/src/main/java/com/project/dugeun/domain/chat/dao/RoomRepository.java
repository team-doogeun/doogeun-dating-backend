package com.project.dugeun.domain.chat.dao;

import com.project.dugeun.domain.chat.domain.Room;
import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByFinalMatch(FinalMatch finalMatch);
}
