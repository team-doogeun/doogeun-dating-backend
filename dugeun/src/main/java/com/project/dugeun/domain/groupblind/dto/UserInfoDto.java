package com.project.dugeun.domain.groupblind.dto;

import com.project.dugeun.domain.user.domain.profile.category.DepartmentType;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private int age;
    private DepartmentType department;
    private GenderType gender;
}
