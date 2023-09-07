package com.project.dugeun.domain.groupblind.dto;

import com.project.dugeun.domain.groupblind.domain.GroupBlindCategory;
import com.project.dugeun.domain.groupblind.domain.GroupBlindStatus;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomSaveRequestDto {

    private String title;


    private Integer capacityMale;

    private Integer capacityFemale;

    private GroupBlindStatus status;

    private GroupBlindCategory groupBlindCategory;

    private String groupBlindIntroduction;
}