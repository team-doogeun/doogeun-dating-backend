package com.project.dugeun.domain.blindDate.domain.dto;

import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.DetailProfile;
import com.project.dugeun.domain.user.domain.profile.category.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.StreamingHttpOutputMessage;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDto {

    private String userId;
    private String name;

     private Integer age;


     private BodyType bodyType;

     private AddressType addressType;

     private DepartmentType departmentType;

     private CharacterType characterType;

     private EmotionType emotionType;

     private MbtiType mbtiType;

     private String basicFilePath;
     private String secondFilePath;
     private String thirdFilePath;



    public MatchResponseDto(User user){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.age =user.getAge();
        this.bodyType = user.getDetailProfile().getBodyType();
        this.addressType = user.getDetailProfile().getAddress();
        this.departmentType = user.getDetailProfile().getDepartment();
        this.characterType = user.getDetailProfile().getCharacter1();
        this.emotionType = user.getDetailProfile().getCharacter2();;
        this.mbtiType = user.getDetailProfile().getMbti();
        this.basicFilePath = user.getBasicFilePath();
        this.secondFilePath = user.getSecondFilePath();
        this.thirdFilePath = user.getThirdFilePath();


    }

    public static MatchResponseDto fromEntity(String userId, String name, String externalId
    , Integer age, BodyType bodyType, AddressType addressType,
                                              DepartmentType departmentType, CharacterType characterType,
                                              EmotionType emotionType, MbtiType mbtiType,
                                              String basicFilePath, String secondFilePath, String thirdFilePath){
        return MatchResponseDto.builder()
                .userId(userId)
                .name(name)
                .age(age)
                .bodyType(bodyType)
                .addressType(addressType)
                .departmentType(departmentType)
                .characterType(characterType)
                .emotionType(emotionType)
                .mbtiType(mbtiType)
                .basicFilePath(basicFilePath)
                .secondFilePath(secondFilePath)
                .thirdFilePath(thirdFilePath)
                .build();

    }

}
