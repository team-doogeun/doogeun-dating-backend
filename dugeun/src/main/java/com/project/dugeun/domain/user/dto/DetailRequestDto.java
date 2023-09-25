package com.project.dugeun.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class DetailRequestDto {
    @NotEmpty
    private String requestUserId; // 해당 유저의 detail를 요구한 유저
    @NotEmpty
    private String targetUserId;

}
