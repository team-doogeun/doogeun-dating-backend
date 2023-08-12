package com.project.dugeun.domain.signup.dto;

import com.project.dugeun.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveResponseDto {

    private String userId;
    private String name;


    public UserSaveResponseDto(User user){
        this.userId = user.getUserId();
        this.name = user.getName();
    }

}
