package com.project.dugeun.domain.user.dto;

import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.category.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailResponseDto {
    private String userId;
    private String name;
    private Integer age;

    private String studentId;

    private Integer height;
    private BodyType bodyType;

    private AddressType addressType;

    private String uniName;

    private DepartmentType departmentType;

    private CharacterType characterType;

    private EmotionType emotionType;

    private HobbyType hobby1;
    private HobbyType hobby2;

    private MbtiType mbtiType;

    private String basicFilePath;
    private String secondFilePath;
    private String thirdFilePath;



    public DetailResponseDto(User user)
    {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.age =user.getAge();
        this.studentId = user.getStudentId();
        this.uniName = user.getUniName();
        this.height = user.getDetailProfile().getHeight();
        this.bodyType = user.getDetailProfile().getBodyType();
        this.addressType = user.getDetailProfile().getAddress();
        this.departmentType = user.getDetailProfile().getDepartment();
        this.characterType = user.getDetailProfile().getFirstCharacter();
        this.emotionType = user.getDetailProfile().getSecondCharacter();
        this.mbtiType = user.getDetailProfile().getMbti();
        this.basicFilePath = user.getBasicFilePath();
        this.secondFilePath = user.getSecondFilePath();
        this.thirdFilePath = user.getThirdFilePath();
        this.hobby1 =user.getDetailProfile().getFirstHobby();
        this.hobby2=user.getDetailProfile().getSecondHobby();

    }

}
