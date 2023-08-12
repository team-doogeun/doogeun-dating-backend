package com.project.dugeun.domain.groupblind.dao;

import com.project.dugeun.domain.groupblind.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Participant findParticipantByUserId(Participant participant);
}
