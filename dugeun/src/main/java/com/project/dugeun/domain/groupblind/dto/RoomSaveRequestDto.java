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

    @NotEmpty
    private String roomId;


    @NotNull
    private Integer capacityMale;

    @NotNull
    private Integer capacityFemale;


    @NotNull
    private GroupBlindStatus status;

    @NotEmpty
    private String groupBlindIntroduction;



}
