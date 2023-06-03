package com.project.dugeun.domain.groupblind.domain;


import lombok.Data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@Builder
public class GroupBlindRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupblind_id")
    private Long id;

    @Column(nullable = false)
    private Integer roomId;

    @Column(nullable = false)
    private String title;

    @Column(name="present_male")
    private int presentMale;
    @Column(name="present_female")
    private int presentFemale;

    @Column(name="capacity_male",nullable = false)
    private int capacityMale;
    @Column(name="capacity_female",nullable = false)
    private int capacityFemale;

    @Builder.Default
    @OneToMany(mappedBy = "groupBlindRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();

    public void addHost(Participant participant) {
        participants.add(participant);
    }

    public void addGuest(Participant participant) {
        participants.add(participant);
    }

    @Enumerated(EnumType.STRING)
    private GroupBlindCategory groupBlindCategory;

    @Enumerated(EnumType.STRING)
    private GroupBlindStatus groupBlindStatus;

    @Enumerated(EnumType.STRING)
    private GroupBlindRole groupBlindRole;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String groupBlindIntroduction;
}