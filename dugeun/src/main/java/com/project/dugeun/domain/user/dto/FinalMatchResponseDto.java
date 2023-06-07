package com.project.dugeun.domain.user.dto;

import com.project.dugeun.domain.finalMatch.domain.FinalMatch;
import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.category.DepartmentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinalMatchResponseDto {

    private String userId;
    private int age;
    private DepartmentType department;

    public static FinalMatchResponseDto fromEntity(User user){
        FinalMatchResponseDto dto = new FinalMatchResponseDto();
        dto.setAge(user.getAge());
        dto.setDepartment(user.getDetailProfile().getDepartment());
        dto.setUserId(user.getUserId());
        return dto;
    }


}


