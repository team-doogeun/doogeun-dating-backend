package com.project.dugeun.domain.signup.application;

import com.project.dugeun.domain.user.application.UserService;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.DetailProfile;
import com.project.dugeun.domain.user.domain.profile.IdealTypeProfile;
import com.project.dugeun.domain.user.domain.profile.category.*;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SignupServiceTest {

    @Autowired
    SignupService signupService;

    @Autowired
    UserService userService;

    User user1 = null;
    @BeforeEach
    void beforeEach() {

        // Given
        // 테스트를 시작 할 때 마다 user1를 새로 생성
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

    }

    @AfterEach
    void afterEach(){
        // 테스트가 끝날 때마다 user1 삭제
        userService.deleteUser(user1.getUserId());
    }

   // Builder를 이용해서 멤버를 생성했을 때 올바르게 생성됐는지 테스트
    @Test
    @DisplayName("1. User Creation Test")
    public void createUser(){

        assertThat(user1.getUserId()).isEqualTo("user1");
        assertThat(user1.getAge()).isEqualTo(25);
        assertThat(user1.getDetailProfile().getDepartment()).isEqualTo(DepartmentType.EDUCATION);

    }

    @Test
    @DisplayName("2. User Deletion Test")
    public void deleteUser(){
        // when - 유저가 삭제될 때
        userService.deleteUser(user1.getUserId());
        // then - 삭제된 유저를 찾을 때 null을 반환하는지 확인
        User deletedUser = userService.findUserByUserId(user1.getUserId());
        assertNull(deletedUser); // 유저가 삭제되었으므로 null 이어야 함
    }

}