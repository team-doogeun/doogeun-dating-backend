package com.project.dugeun.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalIdRequestDto {

    private String userId; // 사용자 ID

    private String targetUserId; // 카카오톡 ID를 원하는 사용자 ID


}
