package com.project.dugeun.domain.groupblind.domain;


import com.project.dugeun.domain.user.domain.profile.category.GenderType;
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
    private String roomId;

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


    @Enumerated(EnumType.STRING)
    private GroupBlindCategory groupBlindCategory;

    @Enumerated(EnumType.STRING)
    private GroupBlindStatus groupBlindStatus;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String groupBlindIntroduction;

    @OneToMany(mappedBy = "groupBlindRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();



}
