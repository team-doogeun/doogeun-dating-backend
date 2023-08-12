package com.project.dugeun.domain.groupblind.dto;

import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
import com.project.dugeun.domain.groupblind.domain.GroupBlindStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupBlindDto {
    private Integer roomId;
    private String title;
    private int presentMale;
    private int presentFemale;
    private int capacityMale;
    private int capacityFemale;
    private GroupBlindStatus groupBlindStatus;

    public GroupBlindDto(GroupBlindRoom room) {
        this.roomId = room.getRoomId();
        this.title = room.getTitle();
        this.presentMale = room.getPresentMale();
        this.presentFemale = room.getPresentFemale();
        this.capacityMale = room.getCapacityMale();
        this.capacityFemale = room.getCapacityFemale();
        this.groupBlindStatus = room.getGroupBlindStatus();
    }
}