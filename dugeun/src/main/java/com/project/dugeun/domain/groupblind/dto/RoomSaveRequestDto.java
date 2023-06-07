package com.project.dugeun.domain.groupblind.dto;

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

    @NotEmpty
    private String title;

    @NotNull
    private Integer capacityMale;

    @NotNull
    private Integer capacityFemale;

    private GroupBlindStatus status;

    @NotEmpty
    private String groupBlindIntroduction;
}