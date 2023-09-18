package com.project.dugeun.domain.likeablePerson.application;

import com.project.dugeun.domain.blindDate.domain.Match;
import com.project.dugeun.domain.likeablePerson.dao.LikeablePersonRepository;
import com.project.dugeun.domain.likeablePerson.domain.LikeablePerson;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.user.domain.profile.DetailProfile;
import com.project.dugeun.domain.user.domain.profile.IdealTypeProfile;
import com.project.dugeun.domain.user.domain.profile.category.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikeablePersonServiceTest {

    @InjectMocks
    private LikeablePersonService likeablePersonService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LikeablePersonRepository likeablePersonRepository;

    @Nested
    @DisplayName("LikeablePerson 관련 테스트")
    class TestLikeablePerson {

        @BeforeEach
        void beforeEach(){}

        @Nested
        @DisplayName("정상 케이스")
        class SuccessCase {
            @Test
            @DisplayName("좋아요 보내기 - 좋아요 데이터가 없는 경우")
            void saveLikeTest_NewLike() {
                // 사용자 데이터 생성
                User user1 = new User();
                user1.setUserId("user1");

                User user2 = new User();
                user2.setUserId("user2");

                // 사용자 데이터 조회 시나리오 설정
                when(userRepository.findByUserId("user1")).thenReturn(user1);
                when(userRepository.findByUserId("user2")).thenReturn(user2);

                // 좋아요 데이터가 없는 상황 설정
                when(likeablePersonRepository.findByFromUserAndToUser(user1, user2)).thenReturn(null);

                // 좋아요 저장 시도
                likeablePersonService.saveLike("user1", "user2");

                // 좋아요 데이터가 없으므로 저장 메소드가 호출되어야 합니다.
                Mockito.verify(likeablePersonRepository, Mockito.times(1)).save(Mockito.any(LikeablePerson.class));
            }


        }

        @Nested
        @DisplayName("비정상 케이스")
        class FailClass{
            @Test
            @DisplayName("좋아요 보내기 - 이미 좋아요 데이터가 있는 경우")
             void saveLikeTest() {
                // 사용자 데이터 생성
                User user1 = new User();
                user1.setUserId("user1");

                User user2 = new User();
                user2.setUserId("user2");

                // 사용자 데이터 조회 시나리오 설정
                when(userRepository.findByUserId("user1")).thenReturn(user1);
                when(userRepository.findByUserId("user2")).thenReturn(user2);

                // 이미 좋아요 데이터가 있는 상황 설정
                when(likeablePersonRepository.findByFromUserAndToUser(user1, user2)).thenReturn(new LikeablePerson());

                // 좋아요 저장 시도
                likeablePersonService.saveLike("user1", "user2");

                // 좋아요 데이터가 이미 존재하므로 저장 메소드가 호출되지 않아야 합니다.
                Mockito.verify(likeablePersonRepository, Mockito.never()).save(Mockito.any(LikeablePerson.class));
            }
        }

    }
}
