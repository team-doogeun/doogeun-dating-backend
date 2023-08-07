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
public class DetailProfile {

    private Integer height;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    private AddressType address;

    @Enumerated(EnumType.STRING)
    private DepartmentType department;

    @Enumerated(EnumType.STRING)
    private CharacterType firstCharacter;

    @Enumerated(EnumType.STRING)
    private EmotionType secondCharacter;

    @Enumerated(EnumType.STRING)
    private HobbyType firstHobby;

    @Enumerated(EnumType.STRING)
    private HobbyType secondHobby;

    @Enumerated(EnumType.STRING)
    private MbtiType mbti;

    @Enumerated(EnumType.STRING)
    private SmokeType smoke;

    @Enumerated(EnumType.STRING)
    private DrinkType drink;


    @Enumerated(EnumType.STRING)
    private PriorityCategory firstPriority;

    @Enumerated(EnumType.STRING)
    private PriorityCategory secondPriority;

    @Enumerated(EnumType.STRING)
    private PriorityCategory thirdPriority;



}
