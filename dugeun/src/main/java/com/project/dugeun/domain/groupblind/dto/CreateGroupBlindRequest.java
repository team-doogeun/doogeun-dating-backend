package com.project.dugeun.domain.groupblind.dto;

import com.project.dugeun.domain.groupblind.domain.GroupBlindStatus;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateGroupBlindRequest {
    private String title;
    private int presentMale;
    private int presentFemale;
    private int capacityMale;
    private int capacityFemale;
    private GroupBlindStatus groupBlindStatus;
    private String groupBlindIntroduction;
    private LocalDateTime startTime = LocalDateTime.now();
    private LocalDateTime endTime = LocalDateTime.now();

    public CreateGroupBlindRequest() {
    }

    public CreateGroupBlindRequest(String title, int presentMale, int presentFemale, int capacityMale, int capacityFemale, GroupBlindStatus groupBlindStatus, String groupBlindIntroduction, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.presentMale = presentMale;
        this.presentFemale = presentFemale;
        this.capacityMale = capacityMale;
        this.capacityFemale = capacityFemale;
        this.groupBlindStatus = groupBlindStatus;
        this.groupBlindIntroduction = groupBlindIntroduction;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
