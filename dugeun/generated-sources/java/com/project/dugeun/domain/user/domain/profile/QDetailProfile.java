package com.project.dugeun.domain.user.domain.profile;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDetailProfile is a Querydsl query type for DetailProfile
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDetailProfile extends BeanPath<DetailProfile> {

    private static final long serialVersionUID = -1739110330L;

    public static final QDetailProfile detailProfile = new QDetailProfile("detailProfile");

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.AddressType> address = createEnum("address", com.project.dugeun.domain.user.domain.profile.category.AddressType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.BodyType> bodyType = createEnum("bodyType", com.project.dugeun.domain.user.domain.profile.category.BodyType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.CharacterType> character1 = createEnum("character1", com.project.dugeun.domain.user.domain.profile.category.CharacterType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.EmotionType> character2 = createEnum("character2", com.project.dugeun.domain.user.domain.profile.category.EmotionType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.DepartmentType> department = createEnum("department", com.project.dugeun.domain.user.domain.profile.category.DepartmentType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.PriorityCategory> firstPriority = createEnum("firstPriority", com.project.dugeun.domain.user.domain.profile.category.PriorityCategory.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.MbtiType> mbti = createEnum("mbti", com.project.dugeun.domain.user.domain.profile.category.MbtiType.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.PriorityCategory> secondPriority = createEnum("secondPriority", com.project.dugeun.domain.user.domain.profile.category.PriorityCategory.class);

    public final EnumPath<com.project.dugeun.domain.user.domain.profile.category.PriorityCategory> thirdPriority = createEnum("thirdPriority", com.project.dugeun.domain.user.domain.profile.category.PriorityCategory.class);

    public QDetailProfile(String variable) {
        super(DetailProfile.class, forVariable(variable));
    }

    public QDetailProfile(Path<? extends DetailProfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDetailProfile(PathMetadata metadata) {
        super(DetailProfile.class, metadata);
    }

}

