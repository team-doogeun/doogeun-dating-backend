package com.project.dugeun.domain.groupblind.domain;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
public class GroupBlindRoom {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupblind_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private int presentMale;
    @Column(nullable = false)
    private int presentFemale;

    @Column(nullable = false)
    private int capacityMale;
    @Column(nullable = false)
    private int capacityFemale;

    @Enumerated(EnumType.STRING)
    private GroupBlindStatus groupBlindStatus;

    @OneToMany(mappedBy = "groupBlindRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    private List<Participant> participants = new ArrayList<>();

    @Column(nullable = false)
    private String groupBlindIntroduction;


    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime = LocalDateTime.now();
}