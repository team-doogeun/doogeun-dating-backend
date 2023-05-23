package com.project.dugeun.domain.blindDate.dto;

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

     private String studentId;

     private Integer height;
     private BodyType bodyType;

     private AddressType addressType;

     private DepartmentType departmentType;

     private CharacterType characterType;

     private EmotionType emotionType;

     private HobbyType hobby1;
     private HobbyType hobby2;

     private MbtiType mbtiType;

     private String basicFilePath;
     private String secondFilePath;
     private String thirdFilePath;


    private String userIdSec;
    private String nameSec;

    private String studentIdSec;
    private Integer ageSec;

    private Integer heightSec;
    private BodyType bodyTypeSec;

    private AddressType addressTypeSec;

    private DepartmentType departmentTypeSec;

    private CharacterType characterTypeSec;

    private EmotionType emotionTypeSec;

    private HobbyType firstHobbySec;
    private HobbyType secondHobbySec;

    private MbtiType mbtiTypeSec;

    private String basicFilePathSec;
    private String secondFilePathSec;
    private String thirdFilePathSec;


    public MatchResponseDto(User user,User userSec){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.age =user.getAge();
        this.studentId = user.getStudentId();
        this.height = user.getDetailProfile().getHeight();
        this.bodyType = user.getDetailProfile().getBodyType();
        this.addressType = user.getDetailProfile().getAddress();
        this.departmentType = user.getDetailProfile().getDepartment();
        this.characterType = user.getDetailProfile().getCharacter1();
        this.emotionType = user.getDetailProfile().getCharacter2();;
        this.mbtiType = user.getDetailProfile().getMbti();
        this.basicFilePath = user.getBasicFilePath();
        this.secondFilePath = user.getSecondFilePath();
        this.thirdFilePath = user.getThirdFilePath();
        this.hobby1 =user.getDetailProfile().getHobby1();
        this.hobby2=user.getDetailProfile().getHobby2();

        this.userIdSec = userSec.getUserId();
        this.nameSec = userSec.getName();
        this.ageSec =userSec.getAge();
        this.studentIdSec = userSec.getStudentId();
        this.heightSec= userSec.getDetailProfile().getHeight();
        this.bodyTypeSec = userSec.getDetailProfile().getBodyType();
        this.addressTypeSec = userSec.getDetailProfile().getAddress();
        this.departmentTypeSec = userSec.getDetailProfile().getDepartment();
        this.characterTypeSec = userSec.getDetailProfile().getCharacter1();
        this.emotionTypeSec = userSec.getDetailProfile().getCharacter2();;
        this.mbtiTypeSec = userSec.getDetailProfile().getMbti();
        this.basicFilePathSec = userSec.getBasicFilePath();
        this.secondFilePathSec = userSec.getSecondFilePath();
        this.thirdFilePathSec = userSec.getThirdFilePath();
        this.firstHobbySec = userSec.getDetailProfile().getHobby1();
        this.secondHobbySec = userSec.getDetailProfile().getHobby2();

    }



}
