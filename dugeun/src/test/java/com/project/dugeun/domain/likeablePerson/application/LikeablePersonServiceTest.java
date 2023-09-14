package com.project.dugeun.domain.likeablePerson.application;

import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.DetailProfile;
import com.project.dugeun.domain.user.domain.profile.IdealTypeProfile;
import com.project.dugeun.domain.user.domain.profile.category.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LikeablePersonServiceTest {

    @Autowired
    LikeablePersonService likeablePersonService;
    User user1 = null;
    User user2 = null;
    Match match = null;

    @BeforeEach
    void beforeEach(){
        // Given
        // 테스트를 시작 할 때 마다 user1, user2를 새로 생성하고 match에 저장
        user1 = User.builder().userId("user1").age(25).description("nice to meet you").email("user1@konkuk.ac.kr")
                .externalId("user1").gender(GenderType.MAN).basicFilePath("d").secondFilePath("d").thirdFilePath("d")
                .name("user1").uniName("건국대학교").studentId("202011762")
                .detailProfile(DetailProfile.builder()
                        .address(AddressType.GANGDONG)
                        .bodyType(BodyType.SLIM)
                        .department(DepartmentType.EDUCATION)
                        .drink(DrinkType.HEAVY)
                        .firstCharacter(CharacterType.CHIC)
                        .secondCharacter(EmotionType.EMOTIONAL)
                        .firstHobby(HobbyType.BADMINTEN)
                        .secondHobby(HobbyType.BIKE)
                        .firstPriority(PriorityCategory.AGE)
                        .secondPriority(PriorityCategory.ADDRESS)
                        .thirdPriority(PriorityCategory.BODY)
                        .height(177)
                        .mbti(MbtiType.INTP)
                        .smoke(SmokeType.NONE).build())
                .idealTypeProfile(IdealTypeProfile.builder()
                        .firstIdealCharacter(CharacterType.INSENSITIVE)
                        .secondIdealCharacter(EmotionType.EMOTIONAL)
                        .firstIdealHobby(HobbyType.BIKE)
                        .secondIdealHobby(HobbyType.FASHION)
                        .idealAge(AgeType.MIDDLE_TWENTY)
                        .idealBodyType(BodyType.MUSCULAR)
                        .idealDepartment(DepartmentType.ENGINEERING)
                        .idealDrink(DrinkType.OFTEN)
                        .idealMbti(MbtiType.ENFJ)
                        .build())
                .build();
        user2 = User.builder().userId("user2").age(24).description("happy dya").email("user2@konkuk.ac.kr")
                .externalId("user2").gender(GenderType.WOMAN).basicFilePath("d").secondFilePath("d").thirdFilePath("d")
                .name("user2").uniName("건국대학교").studentId("202011762")
                .detailProfile(DetailProfile.builder()
                        .address(AddressType.GANGDONG)
                        .bodyType(BodyType.SLIM)
                        .department(DepartmentType.EDUCATION)
                        .drink(DrinkType.HEAVY)
                        .firstCharacter(CharacterType.CHIC)
                        .secondCharacter(EmotionType.EMOTIONAL)
                        .firstHobby(HobbyType.BADMINTEN)
                        .secondHobby(HobbyType.BIKE)
                        .firstPriority(PriorityCategory.AGE)
                        .secondPriority(PriorityCategory.ADDRESS)
                        .thirdPriority(PriorityCategory.BODY)
                        .height(177)
                        .mbti(MbtiType.INTP)
                        .smoke(SmokeType.NONE).build())
                .idealTypeProfile(IdealTypeProfile.builder()
                        .firstIdealCharacter(CharacterType.INSENSITIVE)
                        .secondIdealCharacter(EmotionType.EMOTIONAL)
                        .firstIdealHobby(HobbyType.BIKE)
                        .secondIdealHobby(HobbyType.FASHION)
                        .idealAge(AgeType.MIDDLE_TWENTY)
                        .idealBodyType(BodyType.MUSCULAR)
                        .idealDepartment(DepartmentType.ENGINEERING)
                        .idealDrink(DrinkType.OFTEN)
                        .idealMbti(MbtiType.ENFJ)
                        .build())
                .build();

        match.setUser1(user1);
        match.setUser2(user2);

    }

    @Test
    @DisplayName("1.Send Like Test")
    public void sendLike(){


    }



}
