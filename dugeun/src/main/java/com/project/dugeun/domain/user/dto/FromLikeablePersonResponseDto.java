package com.project.dugeun.domain.user.dto;

import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.domain.profile.category.DepartmentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FromLikeablePersonResponseDto {

    private String userId;
    private int age;
    private DepartmentType department;

    public static FromLikeablePersonResponseDto fromEntity(LikeablePerson likeablePerson){
        FromLikeablePersonResponseDto dto = new FromLikeablePersonResponseDto();
        dto.setAge(likeablePerson.getFromUser().getAge());
        dto.setDepartment(likeablePerson.getFromUser().getDetailProfile().getDepartment());
        dto.setUserId(likeablePerson.getFromUser().getUserId());
        return dto;
    }
}
