package com.project.dugeun.domain.signup.application;

import com.project.dugeun.domain.signup.dto.UserSaveRequestDto;
import com.project.dugeun.domain.user.application.UserService;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.DetailProfile;
import com.project.dugeun.domain.user.domain.profile.IdealTypeProfile;
import com.project.dugeun.domain.user.domain.profile.category.*;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SignupServiceTest {

    @InjectMocks
    private SignupService signupService;

    @Mock
    private UserRepository userRepository;


    @Nested
    @DisplayName("회원가입(기본정보,상세정보,이상형정보 입력)")
    class CreateUser {
        private UserSaveRequestDto dto;

        @BeforeEach
        void befroeEach(){
            dto = UserSaveRequestDto.builder()
                    .userId("user1")
                    .age(25)
                    .description("nice to meet you")
                    .email("user1@konkuk.ac.kr")
                    .externalId("user1")
                    .gender(GenderType.MAN)
                    .basicFilePath("d")
                    .secondFilePath("d")
                    .thirdFilePath("d")
                    .name("user1")
                    .uniName("건국대학교")
                    .studentId("202011762")
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
                            .smoke(SmokeType.NONE)
                            .build())
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

        @Nested
        @DisplayName("정상 케이스")
        class SuccessCase {

            @Test
            @DisplayName("새로운 회원 생성")
            void createUserSuccess1(){

                // Stub: 사용자 아이디, 학번, 이메일이 중복되지 않도록 설정
                Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(null);
                Mockito.when(userRepository.findByName(Mockito.anyString())).thenReturn(null);
                Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);

                // Stub: 사용자 저장 시 생성된 사용자 객체 반환
                Mockito.when(userRepository.save(Mockito.any())).thenReturn(dto.toEntity());

                // When: 회원가입 서비스 호출
                User savedUser = signupService.saveUser(dto);

                // Then: 반환된 사용자 객체와 DTO 정보 비교
                assertThat(savedUser).isNotNull();
                assertThat(savedUser.getUserId()).isEqualTo(dto.getUserId());
                // 나머지 필드에 대해서도 원하는대로 비교

            }
        }

        @Nested
        @DisplayName("비정상 케이스")
        class FailCae{
            @Test
            @DisplayName("중복 회원 생성시 예외 발생 - 이메일 조회")
            void createUserFail1(){
                // Stub: 사용자 아이디, 학번이 중복되지 않도록 설정
                Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(null);
                Mockito.when(userRepository.findByName(Mockito.anyString())).thenReturn(null);

                // 사용자 이메일이 이미 존재한다고 가정
                Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new User());

                // When: 회원가입 서비스 호출
                Exception exception = assertThrows(IllegalStateException.class, () -> {
                    signupService.saveUser(dto);
                });

                // Then: 예외 발생 확인
                assertThat(exception.getMessage()).isEqualTo("이미 가입된 이메일 입니다..");

            }

            @Test
            @DisplayName("중복 회원 생성시 예외 발생 -이름 조회")
            void createUserFail2(){
                // Stub: 사용자 아이디, 학번이 중복되지 않도록 설정
                Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(null);

                // 사용자 이메일이 이미 존재한다고 가정
                Mockito.when(userRepository.findByName(Mockito.anyString())).thenReturn(new User());

                // When: 회원가입 서비스 호출
                Exception exception = assertThrows(IllegalStateException.class, () -> {
                    signupService.saveUser(dto);
                });

                // Then: 예외 발생 확인
                assertThat(exception.getMessage()).isEqualTo("이미 가입된 유저의 이름 입니다..");
            }

            @Test
            @DisplayName("중복 회원 생성시 예외 발생 - 아이디 조회")
            void createUserFail3(){
                // Stub: 사용자 아이디가 중복되지 않도록 설정
                Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(new User());

                // When: 회원가입 서비스 호출
                Exception exception = assertThrows(IllegalStateException.class, () -> {
                    signupService.saveUser(dto);
                });

                // Then: 예외 발생 확인
                assertThat(exception.getMessage()).isEqualTo("이미 가입된 유저 아이디 입니다..");
            }
        }


    }


}