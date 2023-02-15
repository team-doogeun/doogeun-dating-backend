package com.project.dugeun.dto;

import com.project.dugeun.entity.user.User;
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
    public static UserSaveResponseDto fromEntity(String userId,
                                                 String name){
        return UserSaveResponseDto.builder()
                .userId(userId)
                .name(name)
                .build();
    }
}
