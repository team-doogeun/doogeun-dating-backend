package com.project.dugeun.domain.groupblind.dto;

import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupBlindRequest {

    private String title;
    private int capacity;
    private GenderType genderType;

    public CreateGroupBlindRequest(String title, int capacity, GenderType genderType) {
        this.title = title;
        this.capacity = capacity;
        this.genderType = genderType;
    }


}
