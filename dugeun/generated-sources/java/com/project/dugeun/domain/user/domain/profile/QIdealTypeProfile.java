package com.project.dugeun.domain.user.domain.profile;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIdealTypeProfile is a Querydsl query type for IdealTypeProfile
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QIdealTypeProfile extends BeanPath<IdealTypeProfile> {

    private static final long serialVersionUID = 1962158732L;

    public static final QIdealTypeProfile idealTypeProfile = new QIdealTypeProfile("idealTypeProfile");

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.AgeType> idealAge = createEnum("idealAge", com.project.dugeun.domain.user.domain.profile.category.AgeType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.BodyType> idealBodyType = createEnum("idealBodyType", com.project.dugeun.domain.user.domain.profile.category.BodyType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.CharacterType> idealCharacter1 = createEnum("idealCharacter1", com.project.dugeun.domain.user.domain.profile.category.CharacterType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.EmotionType> idealCharacter2 = createEnum("idealCharacter2", com.project.dugeun.domain.user.domain.profile.category.EmotionType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.DepartmentType> idealDepartment = createEnum("idealDepartment", com.project.dugeun.domain.user.domain.profile.category.DepartmentType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.DrinkType> idealDrink = createEnum("idealDrink", com.project.dugeun.domain.user.domain.profile.category.DrinkType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.HeightType> idealHeight = createEnum("idealHeight", com.project.dugeun.domain.user.domain.profile.category.HeightType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.HobbyType> idealHobby1 = createEnum("idealHobby1", com.project.dugeun.domain.user.domain.profile.category.HobbyType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.HobbyType> idealHobby2 = createEnum("idealHobby2", com.project.dugeun.domain.user.domain.profile.category.HobbyType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.MbtiType> idealMbti = createEnum("idealMbti", com.project.dugeun.domain.user.domain.profile.category.MbtiType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.SmokeType> idealSmoke = createEnum("idealSmoke", com.project.dugeun.domain.user.domain.profile.category.SmokeType.class);

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

