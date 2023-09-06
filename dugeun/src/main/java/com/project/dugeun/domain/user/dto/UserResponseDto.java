package com.project.dugeun.domain.user.dto;


import com.project.dugeun.domain.user.domain.profile.DetailProfile;
import com.project.dugeun.domain.user.domain.profile.IdealTypeProfile;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserResponseDto {

    private String userId;
    private String name;
    private String description;
    private String externalId;
    private String uniName;
    private String studentId;
    private String email;
    private Integer age;
    private GenderType gender;

    private DetailProfile detailProfile;
    private IdealTypeProfile idealTypeProfile;

}
