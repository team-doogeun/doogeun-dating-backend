package com.project.dugeun.domain.blindDate.application;

import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.category.GenderType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private ScoreCalculatorService scoreCalculatorService;

    @Nested
    @DisplayName("BlindDate 테스트")
    class BlindDateTest {

        @Nested
        @DisplayName("정상 케이스")
        class SuccessCase{
            @Test
            @DisplayName("매치가 기존에 존재하지 않을 때")
            void checkMatchSuccess(){
                User user1 = new User();
                user1.setUserId("user1");

                User user2 = new User();
                user2.setUserId("user2");

                // user1과 user2 간의 매치가 존재하지 않는 경우를 시뮬레이션
                when(matchRepository.existsByUser1AndUser2(user1, user2)).thenReturn(false);

                boolean result2 = matchService.checkMatch(user1, user2);

                // 매치가 존재하지 않으므로 false를 반환해야 합니다.
                assert (!result2);
            }

            @Test
            @DisplayName("랜덤한 유저 찾기")
            void findRandomUserTest() {
                User user = new User();
                user.setGender(GenderType.MAN);
                List<User> availableUsers = new ArrayList<>();

                // 사용 가능한 유저 목록에 여성 유저를 추가
                User femaleUser = new User();
                femaleUser.setGender(GenderType.WOMAN);
                availableUsers.add(femaleUser);

                // userRepository.findByGenderNotAndUserIdNotIn() 메소드를 호출하면 availableUsers가 반환되도록 설정
                when(userRepository.findByGenderNotAndUserIdNotIn(any(), any())).thenReturn(availableUsers);

                User randomUser = matchService.findRandomUser(GenderType.MAN, new ArrayList<>());

                // 여성 유저 중 랜덤으로 선택되어야 함
                assert (randomUser != null && randomUser.getGender() == GenderType.WOMAN);
            }

            
        }


        @Nested
        @DisplayName("비정상 케이스")
        class FailCase{


            @Test
            @DisplayName("매치(소개)해줄 상대가 없을 때")
            void checkMatchFail1() {
                User user = new User();
                user.setGender(GenderType.MAN);

                // 사용 가능한 유저 목록이 비어있는 상황을 시뮬레이션
                when(userRepository.findByGenderNotAndUserIdNotIn(any(), any())).thenReturn(new ArrayList<>());

                // findRandomUser() 메서드에서 예외를 발생시키는 상황을 테스트
                Exception exception = assertThrows(IllegalStateException.class, () -> {
                    matchService.findRandomUser(GenderType.MAN, new ArrayList<>());
                });

                assertThat(exception.getMessage()).isEqualTo("소개 상대가 없습니다.");
            }


            @Test
            @DisplayName("매치가 이미 존재하는 경우 ")
            void checkMatchFail2(){
                User user1 = new User();
                user1.setUserId("user1");

                User user2 = new User();
                user2.setUserId("user2");

                // user1과 user2 간의 매치가 이미 존재하는 경우를 시뮬레이션
                when(matchRepository.existsByUser1AndUser2(user1, user2)).thenReturn(true);

                boolean result1 = matchService.checkMatch(user1, user2);

                // 이미 매치가 존재하므로 true를 반환해야 합니다.
                assert (result1);

            }


            }
        }


    }

