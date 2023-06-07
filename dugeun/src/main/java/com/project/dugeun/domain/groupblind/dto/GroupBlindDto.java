package com.project.dugeun.domain.groupblind.dto;

import com.project.dugeun.domain.groupblind.domain.GroupBlindRoom;
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


    public GroupBlindDto(GroupBlindRoom room) {
        this.roomId = room.getRoomId();
        this.title = room.getTitle();
        this.presentMale = room.getPresentMale();
        this.presentFemale = room.getPresentFemale();
        this.capacityMale = room.getCapacityMale();
        this.capacityFemale = room.getCapacityFemale();
    }
}