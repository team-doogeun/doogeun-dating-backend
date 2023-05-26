package com.project.dugeun.domain.groupblind.domain;


import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupBlindRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupblind_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int presentMale;
    @Column(nullable = false)
    private int presentFemale;

    @Column(nullable = false)
    private int capacityMale;
    @Column(nullable = false)
    private int capacityFemale;


    @Enumerated(EnumType.STRING)
    private GroupBlindCategory groupBlindCategory;

    @Enumerated(EnumType.STRING)
    private GroupBlindStatus groupBlindStatus;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String groupBlindIntroduction;



}
