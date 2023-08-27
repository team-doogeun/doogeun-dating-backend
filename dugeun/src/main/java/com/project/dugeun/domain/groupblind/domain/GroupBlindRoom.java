package com.project.dugeun.domain.groupblind.domain;


import com.project.dugeun.domain.base.baseEntity.BaseEntity;
import lombok.Data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GroupBlindRoom extends BaseEntity {

    @Column(nullable = false)
    private Integer roomId;

    @Column(nullable = false)
    private String title;

    @Column(name="present_male", nullable = false)
    private int presentMale;
    @Column(name="present_female", nullable = false)
    private int presentFemale;

    @Column(name="capacity_male", nullable = false)
    private int capacityMale;
    @Column(name="capacity_female", nullable = false)
    private int capacityFemale;

    @Column(name = "host_id", nullable = false)
    private String hostId;


    @Builder.Default
    @OneToMany(mappedBy = "groupBlindRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "participants", nullable = false)
    private List<Participant> participants = new ArrayList<>();

    public void addHost(Participant participant) {
        participants.add(participant);
    }

    public void addGuest(Participant participant) {
        participants.add(participant);
    }

    public void removeGuest(Participant participant) {
        participants.remove(participant);
    }

    @Enumerated(EnumType.STRING)
    private GroupBlindCategory groupBlindCategory;

    @Enumerated(EnumType.STRING)
    private GroupBlindStatus groupBlindStatus;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String groupBlindIntroduction;
}