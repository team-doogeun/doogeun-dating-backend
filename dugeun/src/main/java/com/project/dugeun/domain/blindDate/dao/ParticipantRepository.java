package com.project.dugeun.domain.blindDate.dao;

import com.project.dugeun.domain.blindDate.domain.Participant;
import com.project.dugeun.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

 Optional<Participant> findByUser(User user);
}
