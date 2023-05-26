package com.project.dugeun.domain.groupblind.dto;

import com.project.dugeun.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExitRoomResponseDto {

    private String userId;


    public ExitRoomResponseDto(User user){
        this.userId = user.getUserId();
    }

}
