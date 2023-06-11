package com.project.dugeun.domain.groupblind.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class GroupInfoResponseDto {
    private List<UserInfoDto> members;
    private Integer roomId;
    private int presentMale;
    private int presentFemale;
    private String groupBlindIntroduction;
    private String hostId;
    private String title;
}