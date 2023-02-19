package com.project.dugeun.entity.user.profile;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.project.dugeun.model.domain.user.profile.IdealTypeProfile;
import com.project.dugeun.model.domain.user.profile.category.*;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIdealTypeProfile is a Querydsl query type for IdealTypeProfile
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QIdealTypeProfile extends BeanPath<IdealTypeProfile> {

    private static final long serialVersionUID = 1103964699L;

    public static final QIdealTypeProfile idealTypeProfile = new QIdealTypeProfile("idealTypeProfile");

    public final EnumPath<AgeType> idealAge = createEnum("idealAge", AgeType.class);

    public final EnumPath<BodyType> idealBodyType = createEnum("idealBodyType", BodyType.class);

    public final EnumPath<CharacterType> idealCharacter1 = createEnum("idealCharacter1", CharacterType.class);

    public final EnumPath<EmotionType> idealCharacter2 = createEnum("idealCharacter2", EmotionType.class);

    public final EnumPath<DepartmentType> idealDepartment = createEnum("idealDepartment", DepartmentType.class);

    public final EnumPath<DrinkType> idealDrink = createEnum("idealDrink", DrinkType.class);

    public final EnumPath<HeightType> idealHeight = createEnum("idealHeight", HeightType.class);

    public final EnumPath<HobbyType> idealHobby1 = createEnum("idealHobby1", HobbyType.class);

    public final EnumPath<HobbyType> idealHobby2 = createEnum("idealHobby2", HobbyType.class);

    public final EnumPath<MbtiType> idealMbti = createEnum("idealMbti", MbtiType.class);

    public final EnumPath<SmokeType> idealSmoke = createEnum("idealSmoke", SmokeType.class);

    public QIdealTypeProfile(String variable) {
        super(IdealTypeProfile.class, forVariable(variable));
    }

    public QIdealTypeProfile(Path<? extends IdealTypeProfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdealTypeProfile(PathMetadata metadata) {
        super(IdealTypeProfile.class, metadata);
    }

}

