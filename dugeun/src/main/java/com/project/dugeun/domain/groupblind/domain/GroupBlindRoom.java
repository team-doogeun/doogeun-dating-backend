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

//    @OneToMany(mappedBy = "user")
//    private List<User> participants;

    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column GenderType genderType;

//    private int presentMale;
//    private int presentFemale;

    @Enumerated(EnumType.STRING)
    private GroupBlindCategory groupBlindCategory;

    @Enumerated(EnumType.STRING)
    private GroupBlindStatus groupBlindStatus;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Embedded
    private GroupBlindIntroduction groupBlindIntroduction;

    @Builder
    public GroupBlindRoom(String title, int capacity, GenderType genderType) {
        this.title = title;
        this.capacity = capacity;
        this.genderType = genderType;
    }
}
