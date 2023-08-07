package com.project.dugeun.domain.user.domain.profile;


import com.project.dugeun.domain.user.domain.profile.category.*;

import lombok.*;


import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class IdealTypeProfile {
    @Enumerated(EnumType.STRING)
    private AgeType idealAge;

    @Enumerated(EnumType.STRING)
    private HeightType idealHeight;

    @Enumerated(EnumType.STRING)
    private BodyType idealBodyType;

    @Enumerated(EnumType.STRING)
    private DepartmentType idealDepartment;

    @Enumerated(EnumType.STRING)
    private CharacterType firstIdealCharacter;

    @Enumerated(EnumType.STRING)
    private EmotionType secondIdealCharacter;

    @Enumerated(EnumType.STRING)
    private MbtiType idealMbti;

    @Enumerated(EnumType.STRING)
    private HobbyType firstIdealHobby;

    @Enumerated(EnumType.STRING)
    private HobbyType secondIdealHobby;

    @Enumerated(EnumType.STRING)
    private DrinkType idealDrink;

    @Enumerated(EnumType.STRING)
    private SmokeType idealSmoke;




}
