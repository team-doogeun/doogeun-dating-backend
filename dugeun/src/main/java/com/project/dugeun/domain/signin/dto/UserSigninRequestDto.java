package com.project.dugeun.domain.signin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSigninRequestDto {

    private String userId;
    private String name;
    private String password;

    public UserSigninRequestDto() {
    }
}
