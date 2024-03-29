package com.project.dugeun.domain.groupblind.dto;

import com.project.dugeun.domain.groupblind.domain.GroupBlindRole;
import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.GroupBlindStatus;
import com.project.dugeun.domain.groupblind.domain.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomSaveResponseDto {

    private double roomId;
    private String title;
    private Integer capacityMale;
    private Integer capacityFemale;
    private String groupBlindIntroduction;
    private GroupBlindStatus status;
    private String hostId;

    public RoomSaveResponseDto(GroupBlindRoom groupBlindRoom){
        this.roomId = groupBlindRoom.getRoomId();
        this.title = groupBlindRoom.getTitle();
        this.capacityMale= groupBlindRoom.getCapacityMale();
        this.capacityFemale = groupBlindRoom.getCapacityFemale();
        this.groupBlindIntroduction = groupBlindRoom.getGroupBlindIntroduction();
        this.status = groupBlindRoom.getGroupBlindStatus();
        this.hostId = groupBlindRoom.getHostId();
    }
}