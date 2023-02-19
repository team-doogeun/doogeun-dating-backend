package com.project.dugeun.entity.user.profile;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.project.dugeun.model.domain.user.profile.DetailProfile;
import com.project.dugeun.model.domain.user.profile.category.*;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDetailProfile is a Querydsl query type for DetailProfile
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDetailProfile extends BeanPath<DetailProfile> {

    private static final long serialVersionUID = -1739715817L;

    public static final QDetailProfile detailProfile = new QDetailProfile("detailProfile");

    public final EnumPath<AddressType> address = createEnum("address", AddressType.class);

    public final EnumPath<BodyType> bodyType = createEnum("bodyType", BodyType.class);

    public final EnumPath<CharacterType> character1 = createEnum("character1", CharacterType.class);

    public final EnumPath<EmotionType> character2 = createEnum("character2", EmotionType.class);

    public final EnumPath<DepartmentType> department = createEnum("department", DepartmentType.class);

    public final EnumPath<PriorityCategory> firstPriority = createEnum("firstPriority", PriorityCategory.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final EnumPath<MbtiType> mbti = createEnum("mbti", MbtiType.class);

    public final EnumPath<PriorityCategory> secondPriority = createEnum("secondPriority", PriorityCategory.class);

    public final EnumPath<PriorityCategory> thirdPriority = createEnum("thirdPriority", PriorityCategory.class);

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

