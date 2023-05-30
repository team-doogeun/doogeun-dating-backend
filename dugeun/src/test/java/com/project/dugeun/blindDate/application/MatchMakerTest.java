package com.project.dugeun.blindDate.application;
import com.project.dugeun.domain.user.domain.User;
import com.project.dugeun.domain.blindDate.application.MatchMaker;
import com.project.dugeun.domain.blindDate.dao.MatchRepository;
import com.project.dugeun.domain.likeablePerson.application.LikeablePersonService;
import com.project.dugeun.domain.user.dao.UserRepository;
import com.project.dugeun.domain.user.domain.profile.DetailProfile;
import com.project.dugeun.domain.user.domain.profile.IdealTypeProfile;
import com.project.dugeun.domain.user.domain.profile.category.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatchMakerTest {
//
//    @InjectMocks
//    private MatchMaker matchMaker;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private MatchRepository matchRepository;
//
//    @Mock
//    private LikeablePersonService likeablePersonService;
//
//    private User user1;
//    private User user2;
//    private User user3;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.openMocks(this);
//
//        // 테스트에 사용할 User 객체 생성
//        user1 = new User();
//        user1.setUserId("Amy");
//        user1.setAge(25);
//        user1.setGender(GenderType.WOMAN);
//        // user1의 IdealTypeProfile 설정
//        IdealTypeProfile idealTypeProfile1= new IdealTypeProfile();
//        idealTypeProfile1.setIdealDepartment(DepartmentType.BUSINESS);
//        idealTypeProfile1.setIdealAge(AgeType.MIDDLE_TWENTY);
//        idealTypeProfile1.setIdealBodyType(BodyType.MUSCULAR);
//        idealTypeProfile1.setIdealCharacter1(CharacterType.CHIC);
//        idealTypeProfile1.setIdealCharacter2(EmotionType.RATIONAL);
//        idealTypeProfile1.setIdealDrink(DrinkType.OFTEN);
//        idealTypeProfile1.setIdealHeight(HeightType.BETWEEN175_180);
//        idealTypeProfile1.setIdealHobby1(HobbyType.BASKET_BALL);
//        idealTypeProfile1.setIdealHobby2(HobbyType.CLIMB);
//        idealTypeProfile1.setIdealMbti(MbtiType.ENFJ);
//        idealTypeProfile1.setIdealSmoke(SmokeType.NONE);
//        user1.setIdealTypeProfile(idealTypeProfile1);
//
//        // user1의 detailProfile 설정
//        DetailProfile detailProfile = new DetailProfile();
//        detailProfile.setFirstPriority(PriorityCategory.AGE);
//        detailProfile.setSecondPriority(PriorityCategory.BODY);
//        detailProfile.setThirdPriority(PriorityCategory.SMOKE);
//        detailProfile.setAddress(AddressType.GANGDONG);
//        detailProfile.setBodyType(BodyType.SLIM);
//        detailProfile.setCharacter1(CharacterType.LIVELY);
//        detailProfile.setCharacter2(EmotionType.EMOTIONAL);
//        detailProfile.setDepartment(DepartmentType.EDUCATION);
//        detailProfile.setDrink(DrinkType.HEAVY);
//        detailProfile.setSmoke(SmokeType.NONE);
//        detailProfile.setHeight(167);
//        detailProfile.setMbti(MbtiType.INTP);
//        detailProfile.setHobby1(HobbyType.BIKE);
//        detailProfile.setHobby2(HobbyType.TRAVEL);
//        user1.setDetailProfile(detailProfile);
//
//
//
//
//    }


}
