package com.project.dugeun.domain.finalMatch.application;
import com.project.dugeun.domain.finalMatch.dao.FinalMatchRepository;
import com.project.dugeun.domain.likeablePerson.dao.LikeablePersonRepository;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FinalMatchServiceTest {

    @InjectMocks
    private FinalMatchService finalMatchService;

    @Mock
    private FinalMatchRepository finalMatchRepository;

    @Mock
    private LikeablePersonRepository likeablePersonRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("해당 상대가 두근 거린 상대가 없을 때")
    void testSaveFinalMatch_WhenNoLikeablePeople() {
        String userId = "testUser";
        User user = new User();
        user.setUserId(userId);

        when(userRepository.findByUserId(userId)).thenReturn(user);
        when(likeablePersonRepository.findByFromUser(user)).thenReturn(null);


        assertThrows(NullPointerException.class, () -> finalMatchService.saveFinalMatch(userId));
    }

    @Test
    @DisplayName("해당 유저에 해당도는 최종 매칭이 없을 때")
    void testGetFinalMatchedUser_NoMatches() {

        String userId = "testUser";
        User user = new User();
        user.setUserId(userId);
        when(userRepository.findByUserId(userId)).thenReturn(user);
        when(finalMatchRepository.findByUser1OrUser2(user, user)).thenReturn(Collections.emptyList());

        assertThrows(IllegalStateException.class, () -> {
           finalMatchService.getFinalMatchedUser(userId);
        });
    }



}
