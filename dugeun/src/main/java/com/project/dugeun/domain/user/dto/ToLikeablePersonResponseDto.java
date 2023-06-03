package com.project.dugeun.domain.user.dto;

import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.domain.profile.category.DepartmentType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ToLikeablePersonResponseDto {

    private String userId;
    private int age;
    private DepartmentType department;

    public static ToLikeablePersonResponseDto fromEntity(LikeablePerson likeablePerson){
        ToLikeablePersonResponseDto dto = new ToLikeablePersonResponseDto();
        dto.setAge(likeablePerson.getToUser().getAge());
        dto.setDepartment(likeablePerson.getToUser().getDetailProfile().getDepartment());
        dto.setUserId(likeablePerson.getToUser().getUserId());
        return dto;
    }
}
