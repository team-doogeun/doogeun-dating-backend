package com.project.dugeun.domain.finalMatch.dto;

import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.category.DepartmentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinalMatchResponseDto {

    private Long id;
    private String userId;
    private int age;
    private DepartmentType department;



}


