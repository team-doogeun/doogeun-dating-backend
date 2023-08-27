package com.project.dugeun.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.dugeun.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    private LocalDateTime createDate;

    @JsonProperty("updated_at")
    private LocalDateTime modifyDate;

    public static UserDto fromUser(User user) {

        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .createDate(user.getCreateDate())
                .modifyDate(user.getModifyDate())
                .build();
    }
}
